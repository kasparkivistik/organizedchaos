package ee.ttu.idk0071.organizedchaos.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Resource
    private UserService userService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping()
    public User signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(user.getEmail());
        return userService.save(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getById/{id}")
    public User getUserById(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/getByEmail/{email}")
    public User getUserByEmail(@PathVariable("email") String email) { return userService.findByEmail(email);}
}
