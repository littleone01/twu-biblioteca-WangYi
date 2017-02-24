package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.welcome();
        bibliotecaApp.mainMenu();
    }

    private void mainMenu() throws IOException {
        int option;
        while(true) {
            option = this.showMainMenu();
            this.process(option);
        }
    }

    private void process(int option) throws IOException {
        switch (option){
            case 1:
                this.listBooks();
                break;

        }
    }

    private int showMainMenu() throws IOException {
        System.out.println("Main Menu:");
        System.out.println("1. List Books");
        System.out.println("Please select an option: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public void welcome() {
        System.out.println("Welcome!");
    }

    public void listBooks() throws IOException {
        System.out.println("Book List:");
        List<Book> books = getBookList("library.txt");
        for(Book book : books) {
            book.printBookDetails();
        }
    }

    public List<Book> getBookList(String filepath) throws IOException {
        File file = new File(filepath);
        FileInputStream stream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        List<Book> books = new ArrayList<Book>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] detail = line.split("    ");
            books.add(new Book(detail[0], detail[1], detail[2]));
        }
        return books;
    }
}
