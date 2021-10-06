package com.example.MPI_Project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;
    private String date;
    private String deadline;
    private String description;
    private String quality;
    private Double quantity;

    public Order() {
    }

    public Order(String name, String date, String deadline, String description, String quality, Double quantity) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.deadline = deadline;
        this.description = description;
        this.quality = quality;
        this.quantity = quantity;
    }

    public Order(Order anotherOrder) {
        this.id = id;
        this.name = anotherOrder.name;
        this.date = anotherOrder.date;
        this.deadline = anotherOrder.deadline;
        this.description = anotherOrder.description;
        this.quality = anotherOrder.quality;
        this.quantity = anotherOrder.quantity;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.name = date;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
