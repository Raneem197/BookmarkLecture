package com.raneem.bookmarklecture;

public class Slides {


    String title;
    String fipath;
    private int img;
    String booked ;
    String id;


    public Slides(){}
    public Slides(String booked){

        this.booked=booked;
    }


    public Slides(String title, String fipath,int img) {
        this.title = title;
        this.fipath = fipath;
        this.img=img;
    }

    public String getBooked() {
        return booked;
    }

    public void setBooked(String booked) {
        this.booked = booked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFipath() {
        return fipath;
    }

    public void setFipath(String fipath) {
        this.fipath = fipath;
    }




}
