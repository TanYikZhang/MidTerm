package com.example.midterm;

public class Game {
    private String Gamename;
    private String Rating;
    private String Price;
    private String Description;

    public Game(){

    }

    public Game(String Gamename,String Rating,String Price,String Description){
        this.Gamename=Gamename;
        this.Rating=Rating;
        this.Price=Price;
        this.Description=Description;
    }

    public void setGamename(String gamename) {
        Gamename = gamename;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getGamename() {
        return Gamename;
    }

    public String getRating() {
        return Rating;
    }

    public String getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }
}
