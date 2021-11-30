package com.example.MPI_Project.service;

import com.example.MPI_Project.domain.OrderCard;
import com.example.MPI_Project.domain.Task;
import com.example.MPI_Project.repos.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;

    public boolean saveTask(Task task) {
        Task taskFromDB = taskRepo.findByName(task.getName());
        if (taskFromDB != null) {
            return false;
        }
        taskRepo.save(task);
        return true;
    }
    public boolean deleteTask(String name) {
        Task taskFromDB = taskRepo.findByName(name);
        if (taskFromDB != null) {
            taskRepo.deleteByName(name);
            return true;
        }
        return false;
    }
}
