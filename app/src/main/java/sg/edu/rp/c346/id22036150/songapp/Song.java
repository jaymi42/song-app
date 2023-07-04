package sg.edu.rp.c346.id22036150.songapp;

public class Song {
    private static int count = 1;
    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    public Song(String title, String singers, int year, int stars) {
        this.id = count++;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    public int getId() { return id; }

    public String getDescription() { return title; }

    public String getDate() { return singers;}

    public int getYear() { return year;}

    public int getStar() { return stars;}

    public String toString() {
        return id + "\n" + title + "\n" + singers + "\n" + year;
    }

}
