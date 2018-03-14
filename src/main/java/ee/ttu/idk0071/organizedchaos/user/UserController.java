package ee.ttu.idk0071.organizedchaos.user;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserValidator userValidator;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = "application/json")
    public User signUp(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your email or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "{id}", method=RequestMethod.GET)
    public User getUserById(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "{email}", method = RequestMethod.GET)
    public User getUserByEmail(@PathVariable("email") String email) { return userService.findByEmail(email);}
}
