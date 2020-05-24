package com.sine.sineagol.models;

public class Seat {

    private String id;
    private String code;
    private boolean status;
    private Theatre theatre;

    public Seat(){
        this.id = id;
        this.theatre=theatre;
        this.code =null;
        this.status = false;  /// empty=0
    }
    public Seat(String id) {
        this.id = id;
        this.theatre=theatre;
        this.code =null;
        this.status = false;  /// empty=0

    }
    public Theatre getTheatre(){
        return  theatre;
    }
    public void setTheatre(Theatre theatre){
        this.theatre=theatre;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id=id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code=code;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status=status;
    }


}
