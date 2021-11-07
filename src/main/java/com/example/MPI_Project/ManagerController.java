package com.example.MPI_Project;

import com.example.MPI_Project.domain.Task;
import com.example.MPI_Project.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private TaskRepo taskRepo;

    public void putVariables(Map<String, Object> model, Integer id, String name, String deadline, String status, String description, String workman) {
        Iterable<Task> tasks = taskRepo.findAll();
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
    public String start(Map<String, Object> model) {

        putVariables(model, 0,  "",  "",  "",  "",  "");
        return "manager_temp";
    }

    @PostMapping("/create")
    public String createNewTask (
            @RequestParam String newTask_name,
            @RequestParam String newTask_deadline,
            @RequestParam String newTask_status,
            @RequestParam String newTask_description,
            @RequestParam String newTask_workman,
            Map<String, Object> model) {
        Task newTask = new Task(newTask_name, newTask_deadline, newTask_status, newTask_description, newTask_workman);

        putVariables(model, 0,  "",  "",  "",  "",  "");

        if (!newTask_name.equals("") && !newTask_deadline.equals("") && !newTask_status.equals("") && !newTask_description.equals("") && !newTask_workman.equals("")) {
            taskRepo.save(newTask);

        }
        putVariables(model, 0, "", "", "", "", "");
        return "manager_temp";
    }

    @PostMapping("/delete")
    public String deleteTask (@RequestParam Integer deleteTask_id, Map<String, Object> model) {
        taskRepo.deleteById(deleteTask_id);

        putVariables(model, 0,  "",  "",  "",  "",  "");

        return "manager_temp";
    }



    @PostMapping("/choose")
    public String chooseTask (
            @RequestParam Integer chooseTask_id,
            Map<String, Object> model) {

        Task task = findTask(chooseTask_id);
        String task_name = task.getName();
        String task_deadline = task.getDeadline();
        String task_status = task.getStatus();
        String task_description = task.getDescription();
        String task_workman = task.getWorkman();

        putVariables(model, chooseTask_id,  task_name,  task_deadline,  task_status,  task_description,  task_workman);

        return "manager_temp";
    }

    @PostMapping("/edit")
    public String editTask (
            @RequestParam(defaultValue = "0") Integer task_id,
            @RequestParam String task_name,
            @RequestParam String task_deadline,
            @RequestParam String task_status,
            @RequestParam String task_description,
            @RequestParam String task_workman,
            Map<String, Object> model) {


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

        return "manager_temp";
    }

    @PostMapping("/cancel")
    public String cancelTaskEdition (Map<String, Object> model) {
        putVariables(model, 0,  "",  "",  "",  "",  "");

        return "manager_temp";
    }
}


