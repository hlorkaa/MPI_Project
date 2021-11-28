package com.example.MPI_Project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ordername;
    private String customer;
    private String orderdate;
    private String orderdeadline;
    private String quality;
    private Integer quantity;
    private String notes;

    public OrderCard(String name, String customer, String date, String deadline, String quality, Integer quantity, String notes) {
        //this.id = id;
        this.ordername = name;
        this.customer = customer;
        this.orderdate = date;
        this.orderdeadline = deadline;
        this.notes = notes;
        this.quality = quality;
        this.quantity = quantity;
    }

    public OrderCard() {
    }

    public OrderCard(OrderCard anotherOrder) {
        //this.id = id;
        this.ordername = anotherOrder.ordername;
        this.customer = anotherOrder.customer;
        this.orderdate = anotherOrder.orderdate;
        this.orderdeadline = anotherOrder.orderdeadline;
        this.notes = anotherOrder.notes;
        this.quality = anotherOrder.quality;
        this.quantity = anotherOrder.quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String name) {
        this.ordername = name;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDate() {
        return orderdate;
    }

    public void setDate(String date) {
        this.orderdate = date;
    }

    public String getOrderdeadline() {
        return orderdeadline;
    }

    public void setOrderdeadline(String deadline) {
        this.orderdeadline = deadline;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
}
