package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) throws IOException {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.welcome();
        bibliotecaApp.listBooks();
    }

    public void welcome() {
        System.out.println("Welcome!");
    }

    public void listBooks() throws IOException {
        System.out.println("Book List:");
        List<String> books = getBookList("library.txt");
        for(String book : books) {
            System.out.println(book);
        }
    }

    public List<String> getBookList(String filepath) throws IOException {
        File file = new File(filepath);
        FileInputStream stream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        List<String> books = new ArrayList<String>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            books.add(line);
        }
        return books;
    }
}
