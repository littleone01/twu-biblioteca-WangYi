package com.twu.biblioteca;

/**
 * Created by yibwang on 3/1/17.
 */
public class User {
    private String libraryNumber;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public User(String libraryNumber, String password, String name, String email, String phoneNumber) {
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void showInformation() {
        System.out.println("name:" + name);
        System.out.println("email:" + email);
        System.out.println("phoneNumber:" + phoneNumber);
    }
}
