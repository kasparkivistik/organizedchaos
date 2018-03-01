package ee.ttu.idk0071.organizedchaos.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserValidator userValidator;

    @RequestMapping(value = "/users/add", method = RequestMethod.POST, consumes = "application/json")
    public User signUp(@RequestBody User user) {
        userService.save(user);
        return user;
    }

    @RequestMapping(value = "/users/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your email or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/users/{id}", method=RequestMethod.GET)
    public User getUserById(@PathVariable("id") long id) {
        return userService.findById(id);
    }


}
