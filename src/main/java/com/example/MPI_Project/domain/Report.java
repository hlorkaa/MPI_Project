package com.example.MPI_Project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String title;
    private String text;
    private Double sum;

    public Report(String title, String text, Double sum) {
        this.title = title;
        this.text = text;
        this.sum = sum;
    }

    public Report() {
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public Double getSum() {
        return sum;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
