package com.example.MPI_Project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String deadline;
    private String status;
    private String description;
    private String workman;

    public Task() {
    }

    public Task(String name, String deadline, String status, String description, String workman) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.status = status;
        this.description = description;
        this.workman = workman;
    }

    public Task(Task anotherTask) {
        this.id = id;
        this.name = anotherTask.name;
        this.deadline = anotherTask.deadline;
        this.status = anotherTask.status;
        this.description = anotherTask.description;
        this.workman = anotherTask.workman;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkman() {
        return workman;
    }

    public void setWorkman(String workman) {
        this.workman = workman;
    }
}
