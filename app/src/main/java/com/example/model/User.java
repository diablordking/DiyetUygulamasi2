package com.example.model;


public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String weight = "65";
    private String height = "70";
    private String age = "22";


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSurname() {

        return surname;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeight() {
        return height;
    }

    public String getAge() {
        return age;
    }



    public User() {

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


    public User(String id, String email, String weight, String height, String age) {
        this.id = id;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public User(String id, String name, String surname, String email, String weight, String height, String age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }
}
