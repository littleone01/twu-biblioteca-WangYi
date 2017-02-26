package com.twu.biblioteca;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class BibliotecaApp {
    private List<Book> books = new ArrayList<Book>();
    private List<Movie> movies = new ArrayList<Movie>();
    private String bookList = "books.txt";
    private String movieList = "movies.txt";

    public List<Book> getBooks() {
        return books;
    }

    public static void main(String[] args) throws IOException {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.welcome();
        bibliotecaApp.setBooks();
        bibliotecaApp.setMovies();
        bibliotecaApp.mainMenu();
    }

    public void welcome() {
        System.out.println("Welcome!");
    }

    public void setBooks(){
        List<String> lineList = FileUtil.getFileContext(bookList);
        getBookList(lineList);
    }

    public void setMovies() {
        List<String> lineList = FileUtil.getFileContext(movieList);
        getMovieList(lineList);
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
        System.out.println("4. List Movies");
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
            case 4:
                this.listMovies();
                break;
            case 0:
                exit(0);
                break;
            default:
                System.out.println("Select a valid option!");
        }
    }

    private void listMovies() {
        Movie.printDetailTitle();
        for(Movie movie : this.movies) {
            movie.printDetails();
        }
    }

    public void listBooks() {
        Book.printDetailTitle();
        for(Book book : this.books) {
            book.printBookDetails();
        }
    }

    public void getBookList(List<String> lineList){
        for (String line : lineList) {
            String[] detail = line.split("    ");
            this.books.add(new Book(
                    Integer.parseInt(detail[0]),
                    detail[1],
                    Integer.parseInt(detail[2]),
                    detail[3],
                    detail[4]));
        }
    }

    public void checkOutBook() {
        System.out.println("Please input book title you would like to check out:");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.next();

        boolean ifIn = checkOutBookWithTitle( title);

        if (!ifIn) {
            System.out.println("That book is not available.");
        } else {
            List<String> contextToWrite = generateBookFileContext();
            boolean ifSuccess = FileUtil.writeToFile(bookList, contextToWrite);
            if (ifSuccess) {
                System.out.println("Thank you! Enjoy the book");
            }
        }
    }

    private List<String> generateBookFileContext() {
        List<String> bookFileContext = new ArrayList<String>();
        for (Book book : books) {
            bookFileContext.add(book.outputLine());
        }
        return bookFileContext;
    }

    public boolean checkOutBookWithTitle(String title) {
        boolean ifIn = false;
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getStatus().equals("in")) {
                book.setStatus("out");
                ifIn = true;
                break;
            }
        }
        return ifIn;
    }

    public void returnBook() {
        System.out.println("Please input book id you would like to return1:");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.next();

        boolean ifOut = returnBookWithTitle(title);
        if (!ifOut) {
            System.out.println("That is not a valid book to return.");
        } else {
            List<String> contextToWrite = generateBookFileContext();
            boolean ifSuccess = FileUtil.writeToFile(bookList, contextToWrite);
            if (ifSuccess) {
                System.out.println("Thank you for returning the book.");
            }
        }
    }

    public boolean returnBookWithTitle(String title) {
        boolean ifOut = false;
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.getStatus().equals("out")) {
                book.setStatus("in");
                ifOut = true;
                break;
            }
        }
        return ifOut;
    }



    public List<Movie> getMovies() {
        return movies;
    }

    public void getMovieList(List<String> lineList) {
        for (String line : lineList) {
            String[] detail = line.split("    ");
            this.movies.add(new Movie(
                    detail[0],
                    Integer.parseInt(detail[1]),
                    detail[2],
                    Integer.parseInt(detail[3]),
                    detail[4]));
        }
    }
}
