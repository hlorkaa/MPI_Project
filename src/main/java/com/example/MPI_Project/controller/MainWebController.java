package com.example.MPI_Project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/main")
public class MainWebController {

    @GetMapping
    public String start(Map<String, Object> model) {
        return "main_temp";
    }

    @PostMapping("/admin")
    public String goToAdmin (Map<String, Object> model) {
        return "redirect:/admin";
    }
    @PostMapping("/accountant")
    public String goToAccount (Map<String, Object> model) {
        return "redirect:/account";
    }
    @PostMapping("/consultant")
    public String goToCons (Map<String, Object> model) {
        return "redirect:/consultant";
    }
    @PostMapping("/manager")
    public String goToManager (Map<String, Object> model) {
        return "redirect:/manager";
    }
    @PostMapping("/workman")
    public String goToWorker (Map<String, Object> model) {
        return "redirect:/workman";
    }
    @PostMapping("/security")
    public String goToSecurity (Map<String, Object> model) {
        return "redirect:/security";
    }
}


