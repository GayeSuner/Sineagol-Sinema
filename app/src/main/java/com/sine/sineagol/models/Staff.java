package com.sine.sineagol.models;

public class Staff extends Person {

    private double salary;
    private String position;
    private String startDate;


    Staff(){

    }
    Staff(String id, String name, String surname, String phone, String birthdate, String email, double salary, String position, String startDate) {
        super(name, surname, email, phone, birthdate, "1");
        this.salary = salary;
        this.position = position;
        this.startDate = startDate;
    }

    Staff(String id, String name, String surname,String phone, String email, double salary, String position, String startDate) {
        super(name, surname, email,phone, "1");
        this.salary = salary;
        this.position = position;
        this.startDate = startDate;
    }

    public double calculateBonus(){

        return 0;
    }

    public double calculateSalary(){

        return 0;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}