package com.twu.biblioteca;

/**
 * Created by yibwang on 2/24/17.
 */
public class Book {
    private int id;
    private String title;
    private int year;
    private String author;
    private String status;
    private String lenter;

    private static int TITLELENGTHLIMIT = 40;
    private static int IDLENGTH = 4;

    public Book(int id, String title, int year, String author, String status) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.status = status;
    }

    public Book(String line) {
        String[] detail = line.split("    ");
        this.id = Integer.parseInt(detail[0]);
        this.title = detail[1];
        this.year = Integer.parseInt(detail[2]);
        this.author = detail[3];
        this.status = detail[4];
        if (status.equals("out")) {
            this.lenter = detail[5];
        }
    }

    public void setLenter(String lenter) {
        this.lenter = lenter;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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
        if (this.status.equals("in")) {
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
    }

    public int getIdLength(int id) {
        return String.valueOf(id).length();
    }

    public String outputLine() {
        String space = "    ";
        String outLine = this.id + space + this.title + space + this.year + space + this.author + space + this.status;
        if (this.status.equals("out")) {
            outLine = outLine + space + this.lenter;
        }
        return outLine;
    }

    public static void printDetailTitle() {
        System.out.print("ID");
        for(int i = 0; i < IDLENGTH - 2; i ++) {
            System.out.print(" ");
        }
        System.out.print("TITLE");
        for (int i = 0; i < TITLELENGTHLIMIT - 5; i ++) {
            System.out.print(" ");
        }
        System.out.println(" | " + "YEAR" + " | " + "AUTHOR");
    }

    public boolean CheckOut(String title, User user) {
        if (this.title.equals(title) && this.status.equals("in")) {
            this.status = "out";
            this.lenter = user.getLibraryNumber();
            return true;
        } else {
            return false;
        }
    }
}
