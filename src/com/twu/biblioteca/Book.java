package com.twu.biblioteca;

/**
 * Created by yibwang on 2/24/17.
 */
public class Book {
    private int id;
    private String title;
    private int year;
    private String author;
    private int TITLELENGTHLIMIT = 40;
    private int IDLENGTH = 4;

    public Book(int id, String title, int year, String author) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public void printBookDetails() {
        System.out.print(this.id);
        for(int i = 0; i < IDLENGTH - getIdLength(this.id); i ++) {
            System.out.print(" ");
        }
        System.out.print(this.title);
        for (int i = 0; i < TITLELENGTHLIMIT - this.title.length(); i ++) {
            System.out.print(" ");
        }
        System.out.println(" | " + this.year + " | " + this.author);
    }

    public int getIdLength(int id) {
        return String.valueOf(id).length();
    }
}
