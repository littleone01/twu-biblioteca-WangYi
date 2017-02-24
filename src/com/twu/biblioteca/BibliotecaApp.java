package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class BibliotecaApp {
    private List<Book> books = new ArrayList<Book>();
    private String fileName = "library.txt";

    public List<Book> getBooks() {
        return books;
    }

    public static void main(String[] args) throws IOException {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.welcome();
        bibliotecaApp.setBooks();
        bibliotecaApp.mainMenu();
    }

    private void setBooks(){
        getBookList(fileName);
    }

    private void mainMenu() {
        int option;
        while(true) {
            option = showMainMenu();
            process(option);
        }
    }

    private void process(int option) {
        switch (option){
            case 1:
                this.listBooks();
                break;
            case 2:
                this.checkOutBook();
                break;
            case 0:
                exit(0);
                break;
            default:
                System.out.println("Select a valid option!");
        }
    }

    private void checkOutBook() {
        System.out.println("Please input book id you would like to check out:");
        Scanner input = new Scanner(System.in);
        final int bookId = input.nextInt();

        for (Book book : books) {
            if (book.getId() == bookId && book.getStatus().equals("in")) {
                book.setStatus("out");
                break;
            } else {
                System.out.println("That book is not available.");
            }
        }

        boolean ifSuccess = writeToFile(fileName);
        if (ifSuccess) {
            System.out.println("Thank you! Enjoy the book");
        }
    }

    private boolean writeToFile(String fileName) {
        boolean ifSuccess = true;
        try {
            FileOutputStream output = new FileOutputStream(fileName);
            OutputStreamWriter streamWriter = new OutputStreamWriter(output);
            BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
            for (Book book : books) {
                bufferedWriter.write(book.outputLine());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fail to write to file " + fileName);
            ifSuccess = false;
        } finally {
            return ifSuccess;
        }

    }

    private int showMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. List Books");
        System.out.println("2. Check out a book");
        System.out.println("0. Quit");

        System.out.println("Please select an option: ");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public void welcome() {
        System.out.println("Welcome!");
    }

    public void listBooks() {
        System.out.println("Book List:");
        for(Book book : this.books) {
            book.printBookDetails();
        }
    }

    public void getBookList(String filepath){
        try {
            File file = new File(filepath);
            FileInputStream stream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] detail = line.split("    ");
                this.books.add(new Book(Integer.parseInt(detail[0]), detail[1], Integer.parseInt(detail[2]), detail[3], detail[4]));
            }
            stream.close();
        } catch (IOException e) {
            System.out.println("Fail to open file " + filepath);
        }
    }
}
