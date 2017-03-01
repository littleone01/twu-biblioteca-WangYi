package com.twu.biblioteca;

/**
 * Created by yibwang on 3/1/17.
 */
public class User {
    private String name;
    private String password;
    private String email;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public User(String name, String password, String email, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void showInformation() {
        System.out.println("name:" + name);
        System.out.println("password:" + password);
        System.out.println("email:" + email);
        System.out.println("phoneNumber:" + phoneNumber);
    }
}
