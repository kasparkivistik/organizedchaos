package ee.ttu.idk0071.organizedchaos.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserValidator userValidator;

    @RequestMapping(value = "/auth/signup", method = RequestMethod.GET)
    public String signUp(Model model) {
        model.addAttribute("userForm", new User());
        return "signup";
    }

    @RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your email or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "";
    }
}
