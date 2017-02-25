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

    public void welcome() {
        System.out.println("Welcome!");
    }

    public void setBooks(){
        getBookList(fileName);
    }

    private void mainMenu() {
        int option;
        while(true) {
            option = showMainMenu();
            process(option);
        }
    }

    private int showMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. List Books");
        System.out.println("2. Check Out a Book");
        System.out.println("3. Return Book");
        System.out.println("0. Quit");

        System.out.println("Please select an option: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void process(int option) {
        switch (option){
            case 1:
                this.listBooks();
                break;
            case 2:
                this.checkOutBook();
                break;
            case 3:
                this.returnBook();
                break;
            case 0:
                exit(0);
                break;
            default:
                System.out.println("Select a valid option!");
        }
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

    public void checkOutBook() {
        System.out.println("Please input book id you would like to check out:");
        Scanner scanner = new Scanner(System.in);
        int bookId = scanner.nextInt();

        boolean ifIn = checkOutBookWithId(bookId);

        if (!ifIn) {
            System.out.println("That book is not available.");
        } else {
            boolean ifSuccess = writeToFile(fileName);
            if (ifSuccess) {
                System.out.println("Thank you! Enjoy the book");
            }
        }
    }

    public boolean checkOutBookWithId(int bookId) {
        boolean ifIn = false;
        for (Book book : books) {
            if (book.getId() == bookId && book.getStatus().equals("in")) {
                book.setStatus("out");
                ifIn = true;
                break;
            }
        }
        return ifIn;
    }

    public void returnBook() {
        System.out.println("Please input book id you would like to check out:");
        Scanner scanner = new Scanner(System.in);
        int bookId = scanner.nextInt();

        boolean ifOut = returnBookWithId(bookId);
        if (!ifOut) {
            System.out.println("That is not a valid book to return.");
        } else {
            boolean ifSuccess = writeToFile(fileName);
            if (ifSuccess) {
                System.out.println("Thank you for returning the book.");
            }
        }
    }

    public boolean returnBookWithId(int bookId) {
        boolean ifOut = false;
        for (Book book : books) {
            if (book.getId() == bookId && book.getStatus().equals("out")) {
                book.setStatus("in");
                ifOut = true;
                break;
            }
        }
        return ifOut;
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
}
