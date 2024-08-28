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



        @GetMapping("")
        public String users(Model model) {
            model.addAttribute("users", userService.findAll());
            return "users";
        }

        @GetMapping("/add")
        public String addUser(Model model) {
            model.addAttribute("user", new User());
            return "addUser";
        }

        @PostMapping("")
        public String addUser(@ModelAttribute("user") User user) {

            userService.save(user);
            return "redirect:/users";
        }

    }



