package com.example.MPI_Project.controller;

import com.example.MPI_Project.domain.Role;
import com.example.MPI_Project.domain.User;
import com.example.MPI_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public Callable<String> signUp(Model model) {
        return () -> {
            model.addAttribute("userForm", new User());

            return "auth/signUp_temp";
        };
    }

    @PostMapping("/signUp")
    public Callable<String> addUser(
            @RequestParam String username,
            @RequestParam String role,
            @RequestParam String password,
            Map<String, Object> model) {
        return () -> {
            User newUser = new User(username, password, Role.valueOf(role));

            if (!username.equals("") && !password.equals("")) {
                if (!userService.saveUser(newUser)) {
                    return "auth/signUp_temp";
                }
            }

            return "redirect:/admin";
        };
    }

    @GetMapping("/login")
    public Callable<String> login() {
        return () -> "auth/login_temp";
    }

    @GetMapping("/login/result")
    public Callable<String> loginSuccess() {
        return () -> "auth/loginSuccess_temp";
    }

    @GetMapping("/logout/result")
    public Callable<String> logoutSuccess() {
        return () -> "auth/logout_temp";
    }

    @GetMapping("/denied")
    public Callable<String> denied() {
        return () -> "auth/denied_temp";
    }

    @PostMapping("/exit")
    public Callable<String> goToMain (Map<String, Object> model) {
        return () -> "redirect:/main";
    }

}
