package dev.group.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller

public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //this is the display of user collection page
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        String test = userService.findAll().toString();
        System.out.println(test);
        return "users";
    }


    @GetMapping("/users/add")
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



