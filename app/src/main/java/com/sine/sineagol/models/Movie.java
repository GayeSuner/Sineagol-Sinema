package com.sine.sineagol.models;

import java.util.ArrayList;

public class Movie
{
    private String id;
    private String name;
    private String description;
    private Theatre theatre;
    private String category;
    private double score;
    private String URLPic;
    private ArrayList<String> comments;


    public Movie(){

    }
    public Movie( String id, String name, String description, Theatre theatre,double score, String category,String URLPic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.theatre = theatre;
        this.category = category;
        this.score=score;
        this.URLPic=URLPic;
        comments = new ArrayList<>();
    }

    public void giveScore(int score){

    }

    public  String getId() {
        return id;
    }

    public void setId( String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    public String getURLPic(){
        return URLPic;
    }
    public void setURLPic(String url){
        this.URLPic=url;
    }
}