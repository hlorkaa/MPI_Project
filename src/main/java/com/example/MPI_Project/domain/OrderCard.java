package com.example.MPI_Project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderCard {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer orderID;

    private String orderName;
    private String customer;
    private String date;
    private String orderDeadline;
    private String quality;
    private Integer quantity;
    private String notes;

    public OrderCard() {
    }

    public OrderCard(String name, String customer, String date, String deadline, String quality, Integer quantity, String notes) {
        //this.id = id;
        this.orderName = name;
        this.customer = customer;
        this.date = date;
        this.orderDeadline = deadline;
        this.quality = quality;
        this.quantity = quantity;
        this.notes = notes;
    }

    public OrderCard(OrderCard anotherOrderCard) {
        //this.id = id;
        this.orderName = anotherOrderCard.orderName;
        this.customer = anotherOrderCard.customer;
        this.date = anotherOrderCard.date;
        this.orderDeadline = anotherOrderCard.orderDeadline;
        this.quality = anotherOrderCard.quality;
        this.quantity = anotherOrderCard.quantity;
        this.notes = anotherOrderCard.notes;
    }



    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer id) {
        this.orderID = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String name) {
        this.orderName = name;
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
        this.orderName = date;
    }

    public String getOrderDeadline() {
        return orderDeadline;
    }

    public void setOrderDeadline(String deadline) {
        this.orderDeadline = deadline;
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
