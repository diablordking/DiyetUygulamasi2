package com.example.model;

public class Dater {

    private long date;
    private Calorie calorie;

    public  Dater () {

    }
    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Calorie getCalorie() {
        return calorie;
    }

    public void setCalorie(Calorie calorie) {
        this.calorie = calorie;
    }

    public Dater(long date, Calorie calorie) {

        this.date = date;
        this.calorie = calorie;
    }
}
