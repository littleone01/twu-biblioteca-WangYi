package com.twu.biblioteca;

/**
 * Created by yibwang on 2/24/17.
 */
public class Book {
    private String title;
    private String year;
    private String author;
    private int TITLELENGTHLIMIT = 40;

    public Book(String title, String year, String author) {
        this.title = title;
        this.year = year;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    public void printBookDetails() {
        System.out.print(this.title);
        for (int i = 0; i < TITLELENGTHLIMIT - this.title.length(); i ++) {
            System.out.print(" ");
        }
        System.out.println(" | " + this.year + " | " + this.author);
    }
}
