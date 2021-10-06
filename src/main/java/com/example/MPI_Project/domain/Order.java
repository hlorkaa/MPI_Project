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
    private String customer;
    private String date;
    private String deadline;
    private String quality;
    private Integer quantity;
    private String notes;

    public Order() {
    }

    public Order(String name, String customer, String date, String deadline, String quality, Integer quantity, String notes) {
        //this.id = id;
        this.name = name;
        this.customer = customer;
        this.date = date;
        this.deadline = deadline;
        this.quality = quality;
        this.quantity = quantity;
        this.notes = notes;
    }

    public Order(Order anotherOrder) {
        //this.id = id;
        this.name = anotherOrder.name;
        this.customer = anotherOrder.customer;
        this.date = anotherOrder.date;
        this.deadline = anotherOrder.deadline;
        this.quality = anotherOrder.quality;
        this.quantity = anotherOrder.quantity;
        this.notes = anotherOrder.notes;
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
