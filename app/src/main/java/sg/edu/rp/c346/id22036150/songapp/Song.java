package sg.edu.rp.c346.id22036150.songapp;

import java.io.Serializable;

public class Song implements Serializable {
    private static int count = 1;
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(String title, String singers, int year, int stars) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public Song(int id, String title, String singers, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() { return id; }

    public String getTitle() { return title; }

    public String getSingers() { return singers;}

    public int getYear() { return year;}

    public int getStar() { return stars;}

    public void setSongContent(String title, String singer, int year, int stars){
        this.title = title;
        this.singers = singer;
        this.year = year;
        this.stars = stars;
    }

    public void setID(int id){
        this.id = id;
    }

    public String toString() {
        String starString = "*".repeat(stars);
        String output = String.format("Title: %s\nArtist: %s\nYear: %d\nRating: %s", title, singers, year, starString);
        return output;
    }

}
