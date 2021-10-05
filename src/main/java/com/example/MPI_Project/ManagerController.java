package com.example.MPI_Project;

import com.example.MPI_Project.domain.Task;
import com.example.MPI_Project.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private TaskRepo taskRepo;

    @GetMapping
    public String showAllTasks (Map<String, Object> model) {
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        model.put("task_name", "");
        model.put("task_deadline", "");
        model.put("task_status", "");
        model.put("task_description", "");
        model.put("task_workman", "");
        return "manager";
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
        taskRepo.save(newTask);


        String task_name = "";

        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        model.put("task_name", "");
        model.put("task_deadline", "");
        model.put("task_status", "");
        model.put("task_description", "");
        model.put("task_workman", "");

        return "manager";
    }

    @PostMapping("/delete")
    public String deleteTask (@RequestParam Integer deleteTask_id, Map<String, Object> model) {
        taskRepo.deleteById(deleteTask_id);

        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        model.put("task_name", "");
        model.put("task_deadline", "");
        model.put("task_status", "");
        model.put("task_description", "");
        model.put("task_workman", "");

        return "manager";
    }

    public Task findTask(Integer id) {
        return taskRepo.findById(id).orElse(new Task());
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
        
        Iterable<Task> tasks = taskRepo.findAll();
        model.put("tasks", tasks);
        model.put("task_name", task_name);
        model.put("task_deadline", task_deadline);
        model.put("task_status", task_status);
        model.put("task_description", task_description);
        model.put("task_workman", task_workman);

        //taskRepo.save(task);

        return "manager";
    }

}
