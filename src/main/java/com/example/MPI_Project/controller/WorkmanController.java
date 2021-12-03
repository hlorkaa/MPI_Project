package com.example.MPI_Project.controller;

import com.example.MPI_Project.config.SecurityHelper;
import com.example.MPI_Project.domain.Task;
import com.example.MPI_Project.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.Callable;

@Controller
@RequestMapping("/workman")
public class WorkmanController {
    @Autowired
    private TaskRepo taskRepo;
    private SecurityHelper secHelper;

    @PostConstruct
    public void initialize() {
        secHelper = new SecurityHelper(SecurityContextHolder.getContext());
    }

    public void putVariables(Map<String, Object> model, Integer id, String name, String deadline, String status, String description, String workman) {

        String workmanName = secHelper.userName();
        Iterable<Task> tasks = taskRepo.findByWorkman(workmanName);
        model.put("tasks", tasks);
        model.put("task_id", id);
        model.put("task_name", name);
        model.put("task_deadline", deadline);
        model.put("task_status", status);
        model.put("task_description", description);
        model.put("task_workman", workman);
    }

    public Task findTask(Integer id) {
        return taskRepo.findById(id).orElse(new Task());
    }

    @GetMapping
    public Callable<String> start (
            @RequestParam(name="workman_name", required=false, defaultValue="") String workman_name,
            Map<String, Object> model) {
        return () -> {
            putVariables(model, 0,  "",  "",  "",  "",  "");
            return "workman_temp";
        };
    }

    @PostMapping("/choose")
    public Callable<String> chooseTask (
            @RequestParam(name="workman_name", required=false, defaultValue="") String workman_name,
            @RequestParam Integer chooseTask_id,
            Map<String, Object> model) {
        return () -> {
            Task task = findTask(chooseTask_id);
            String task_name = task.getName();
            String task_deadline = task.getDeadline();
            String task_status = task.getStatus();
            String task_description = task.getDescription();
            String task_workman = task.getWorkman();

            putVariables(model, chooseTask_id,  task_name,  task_deadline,  task_status,  task_description,  task_workman);

            return "workman_temp";
        };
    }

    @PostMapping("/edit")
    public Callable<String> editTask (
            @RequestParam(name="workman_name", required=false, defaultValue="") String workman_name,
            @RequestParam Integer task_id,
            @RequestParam String task_name,
            @RequestParam String task_deadline,
            @RequestParam String task_status,
            @RequestParam String task_description,
            @RequestParam String task_workman,
            Map<String, Object> model) {
        return () -> {
            if (task_id != 0 && !task_name.equals("") && !task_deadline.equals("") && !task_status.equals("") && !task_description.equals("") && !task_workman.equals("")) {
                Task task = findTask(task_id);
                task.setName(task_name);
                task.setDeadline(task_deadline);
                task.setStatus(task_status);
                task.setDescription(task_description);
                task.setWorkman(task_workman);
                taskRepo.save(task);
            }

            putVariables(model, 0,  "",  "",  "",  "",  "");

            return "workman_temp";
        };
    }

    @PostMapping("/cancel")
    public Callable<String> cancelTaskEdition (
            @RequestParam(name="workman_name", required=false, defaultValue="") String workman_name,
            Map<String, Object> model) {
        return () -> {
            putVariables(model, 0,  "",  "",  "",  "",  "");

            return "workman_temp";
        };
    }

    @PostMapping("/exit")
    public Callable<String> goToMain (Map<String, Object> model) {
        return () -> "redirect:/main";
    }
}
