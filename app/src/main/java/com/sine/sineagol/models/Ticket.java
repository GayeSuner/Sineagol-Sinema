package com.sine.sineagol.models;


public class Ticket {

    private int id;
    private int ticketPrice;
    private Customer customer;
    private Seat seat;
    public Ticket(Customer customer,Seat seat,int price) {
        this.customer = customer;
        this.seat=seat;
        ticketPrice=price;

    }

    public int getPrice(){
        return ticketPrice;
    }
    public void setPrice(int price){
        this.ticketPrice=price;
    }
    public Customer getCustomer(){
        return this.customer;
    }
    public void setCustomer(Customer customer){
        this.customer=customer;
    }
    public Seat getSeat(){
        return this.seat;
    }
    public void setSeat(Seat seat){
        this.seat=seat;
    }




}

