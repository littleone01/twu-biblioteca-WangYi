package com.twu.biblioteca;

/**
 * Created by yibwang on 2/25/17.
 */
public class Movie {
    private String name;
    private int year;
    private String director;
    private int rating;
    private String status;
    private static int NAMELENGTH = 40;

    public Movie(String name, int year, String director, int rating, String status) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.status = status;
    }

    public Movie(String line) {
        String[] detail = line.split("    ");
        this.name = detail[0];
        this.year = Integer.parseInt(detail[1]);
        this.director = detail[2];
        this.rating = Integer.parseInt(detail[3]);
        this.status = detail[4];
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void printDetails() {
        if (this.status.equals("in")) {
            System.out.print(this.name);
            for (int i = 0; i < NAMELENGTH - this.name.length(); i ++) {
                System.out.print(" ");
            }
            System.out.println(" | " + this.year + " | " + this.rating + " | " + this.director);
        }
    }

    public static void printDetailTitle() {
        System.out.print("NAME");
        for (int i = 0; i < NAMELENGTH - 4; i ++) {
            System.out.print(" ");
        }
        System.out.println(" | " + "YEAR" + " | " + "R" + " | " + "DIRECTOR");
    }

    public String outputLine() {
        String space = "    ";
        return this.name + space + this.year + space + this.director + space + this.rating + space + this.status;
    }
}
