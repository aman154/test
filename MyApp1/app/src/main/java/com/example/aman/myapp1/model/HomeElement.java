package com.example.aman.myapp1.model;

/**
 * Created by aman on m1/m7/m15.
 */
public class HomeElement {

    private int image;
    private String title;

    public HomeElement(int image, String title){
        this.image = image;
        this.title = title;
    }

    public int getImage(){

        return image;
    }

    public String getTitle(){
        return title;
    }
}
