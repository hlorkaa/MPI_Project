package com.example.MPI_Project;

import com.example.MPI_Project.domain.Task;
import com.example.MPI_Project.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/acc")
    public String goToAccount (Map<String, Object> model) {
        return "redirect:/account_dep";
    }
    @PostMapping("/cons")
    public String goToCons (Map<String, Object> model) {
        return "redirect:/consultant";
    }
    @PostMapping("/manag")
    public String goToManager (Map<String, Object> model) {
        return "redirect:/manager";
    }
    @PostMapping("/worker")
    public String goToWorker (Map<String, Object> model) {
        return "redirect:/workman";
    }
}


