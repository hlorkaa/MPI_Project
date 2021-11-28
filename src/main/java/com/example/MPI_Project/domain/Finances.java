package com.example.MPI_Project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Finances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private Double amount;
    private String type;

    public Finances(){
    }

    public Finances(String date, Double amount, String type) {
        //this.id = id;
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public Finances(Finances anotherFinances) {
        //this.id = id;
        this.date = anotherFinances.date;
        this.amount = anotherFinances.amount;
        this.type = anotherFinances.type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
