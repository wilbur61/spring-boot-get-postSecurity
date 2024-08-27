package dev.group.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    //give our model a placeholder of attribute key "user" and have the value point to an empty user class new User()
    @ModelAttribute("user")
    public User user() {
        System.out.println("IN  UserRegController->UserService()");
        return new User();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        System.out.println("IN  UserRegController->showRegistrationForm()");
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") User user, BindingResult result){

        System.out.println("IN  POST MAPPING UserRegController->registerUserAccount()");
        User existing = userService.findByUsername(user.getUsername());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        System.out.println("ZZZZZZZZZZZZZZZ result:"+result.toString());

        if (result.hasErrors()){
            System.out.println("result:"+result.toString());
            return "registration";
        }
        userService.save(user);


        return "redirect:/private";
    }

//        @GetMapping("")
//        public String users(Model model) {
//            model.addAttribute("users", userService.findAll());
//            return "users";
//        }

//        @GetMapping("/add")
//        public String addUser(Model model) {
//            model.addAttribute("user", new User());
//            return "addUser";
//        }
//
//        @PostMapping("")
//        public String addUser(@ModelAttribute("user") User user) {
//
//            userService.save(user);
//            return "redirect:/users";
//        }

    }



