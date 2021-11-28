package com.example.MPI_Project.controller;

import com.example.MPI_Project.domain.User;
import com.example.MPI_Project.repos.UserRepo;
import com.example.MPI_Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    public void putVariables(Map<String, Object> model, Integer id, String username, String role) {
        Iterable<User> users = userRepo.findAll();
        model.put("users", users);
        model.put("user_id", id);
        model.put("user_name", username);
        model.put("user_role", role);
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin_temp";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam Integer deleteUser_id, Map<String, Object> model) {
        userService.deleteUser(deleteUser_id);
        putVariables(model, 0, "", "");

        return "admin_temp";
    }

    @PostMapping("/exit")
    public String goToMain (Map<String, Object> model) {
        return "redirect:/main";
    }

}
