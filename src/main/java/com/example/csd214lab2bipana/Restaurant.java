package com.example.csd214lab2bipana;

public class Restaurant {
    private int id;
    private String Managers;
    private String Departments;
    private int Workers;

    public Restaurant(int id, String Managers, String Departments, int Workers) {
        this.id = id;
        this.Managers = Managers;
        this.Departments = Departments;
        this.Workers= Workers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManagers() {
        return Managers;
    }

    public void setManagers(String managers) {
        Managers = managers;
    }

    public String getDepartments() {
        return Departments;
    }

    public void setDepartments(String departments) {
        Departments = departments;
    }

    public Integer getWorkers() {
        return Workers;
    }

    public void setWorkers(Integer workers) {
        Workers = workers;
    }


}

