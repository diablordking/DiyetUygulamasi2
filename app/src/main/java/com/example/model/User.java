package com.example.model;


public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private int weight = 60;
    private int height = 70;
    private int age = 20;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSurname() {

        return surname;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }



    public User(String id, String s, String toString) {

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String customerName) {
        this.name = customerName;
    }


    public User(String id, String email, int weight, int height, int age) {
        this.id = id;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public User(String id, String name, String surname, String email, int weight, int height, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }
}
