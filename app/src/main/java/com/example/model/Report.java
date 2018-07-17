package com.example.model;

public class Report {
    private Dater date;

    public Report() {

    }

    public Dater getDate() {
        return date;
    }

    public void setDate(Dater date) {
        this.date = date;
    }

    public Report(Dater date) {

        this.date = date;
    }
}
