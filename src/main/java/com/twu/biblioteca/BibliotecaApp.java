package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class BibliotecaApp {
    private List<Book> books = new ArrayList<Book>();
    private List<Movie> movies = new ArrayList<Movie>();
    private String bookList = "books.txt";
    private String movieList = "movies.txt";
    private String userList = "user.txt";
    private User user = null;
    private Scanner scanner = new Scanner(System.in);

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

    public int showMainMenu() {
        String showLog = getInOrOutByUser(user);
        System.out.println("Main Menu:");
        System.out.println("1. List Books");
        System.out.println("2. Check Out a Book");
        System.out.println("3. Return Book");
        System.out.println("4. List Movies");
        System.out.println("5. Check Out Movie");

        System.out.println("8. Log " + showLog);
        if (user != null) {
            System.out.println("9. Show Personal Information");
        }
        System.out.println("0. Quit");

        System.out.println("Please select an option: ");

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
            case 5:
                this.checkOutMovie();
                break;
            case 8:
                this.logInandLogOut();
                break;
            case 9:
                this.showUserInformation();
                break;
            case 0:
                exit(0);
                break;
            default:
                System.out.println("Select a valid option!");
        }
    }

    private void showUserInformation() {
        user.showInformation();
    }

    private void logInandLogOut() {
        if (user == null) {
            Login();
        } else {
            user = null;
        }
    }

    private void Login() {
        try {
            BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
            System.out.println("username:");
            String username = strin.readLine();
            System.out.println("password:");
            String password = strin.readLine();

            List<String> userLineList = FileUtil.getFileContext(userList);
            user = getUser(username, password, userLineList);
        } catch (IOException e) {
            e.printStackTrace();
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
            this.books.add(new Book(line));
        }
    }

    public void checkOutBook() {
        System.out.println("Please input book title you would like to check out:");
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
            this.movies.add(new Movie(line));
        }
    }

    private void checkOutMovie() {
        System.out.println("Please input movie name you would like to check out:");
        String name = scanner.nextLine();

        boolean ifIn = checkOutMovieWithName(name);

        if (!ifIn) {
            System.out.println("That movie is not available.");
        } else {
            List<String> contextToWrite = generateMovieFileContext();
            boolean ifSuccess = FileUtil.writeToFile(movieList, contextToWrite);
            if (ifSuccess) {
                System.out.println("Thank you! Enjoy the movie");
            }
        }
    }

    private List<String> generateMovieFileContext() {
        List<String> movieFileContext = new ArrayList<String>();
        for (Movie movie : movies) {
            movieFileContext.add(movie.outputLine());
        }
        return movieFileContext;
    }

    public boolean checkOutMovieWithName(String movieName) {
        boolean ifIn = false;
        for (Movie movie : movies) {
            if (movie.getName().equals(movieName) && movie.getStatus().equals("in")) {
                movie.setStatus("out");
                ifIn = true;
                break;
            }
        }
        return ifIn;
    }

    public String getInOrOutByUser(User user) {
        return user == null ? "in" : "out";
    }

    public User getUser(String name, String password, List<String> userLineList) {
        User user = null;
        boolean ifWrongPassword = false;
        for (String line : userLineList) {
            String[] details = line.split("    ");
            if (name.equals(details[0])) {
                if (password.equals(details[1])) {
                    user = new User(details[0], details[1], details[2], details[3]);
                } else {
                    System.out.println("Wrong password!");
                    ifWrongPassword = true;
                }
                break;
            }
        }
        if (!ifWrongPassword && user == null) {
            System.out.println("Username is not exists!");
        }
        return user;
    }
}
