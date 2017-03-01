package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;


import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {
    BibliotecaApp bibliotecaApp;
    List<Book> books;
    List<Movie> movies;

    @Before
    public void setUp() throws Exception {
        bibliotecaApp = new BibliotecaApp();
        List<String> lineList_book = FileUtil.getFileContext("src/test/java/com/twu/biblioteca/test_lib_book.txt");
        bibliotecaApp.getBookList(lineList_book);
        books = bibliotecaApp.getBooks();
        List<String> lineList_movie = FileUtil.getFileContext("src/test/java/com/twu/biblioteca/test_lib_movie.txt");
        bibliotecaApp.getMovieList(lineList_movie);
        movies = bibliotecaApp.getMovies();
        User user = new User("000-0000", "pa", "name", "em", "pn");
        bibliotecaApp.setUser(user);
    }

    @Test
    public void should_get_book_list_in_book_file() throws IOException {
        assertEquals(2, books.size());

        Book refactor = books.get(0);
        Book tdd = books.get(1);

        assertEquals(1, refactor.getId());
        assertEquals("Refactor", refactor.getTitle());
        assertEquals(2003, refactor.getYear());
        assertEquals("MartinFowler", refactor.getAuthor());
        assertEquals("in", refactor.getStatus());

        assertEquals(2, tdd.getId());
        assertEquals("TDD", tdd.getTitle());
        assertEquals(2003, tdd.getYear());
        assertEquals("Beck", tdd.getAuthor());
    }

    @Test
    public void should_set_book_status_out_when_check_out_a_book() {
        boolean ifInOfId1 = ProcessUtil.checkOutBookWithTitle("Refactor", bibliotecaApp);
        boolean ifInOfId2 = ProcessUtil.checkOutBookWithTitle("TDD", bibliotecaApp);

        assertEquals("out", books.get(0).getStatus());
        assertEquals(true, ifInOfId1);
        assertEquals(false, ifInOfId2);
    }

    @Test
    public void should_set_book_status_in_when_return_a_book() {
        boolean ifOutOfId2 = bibliotecaApp.returnBookWithTitle("TDD");

        assertEquals("in", books.get(1).getStatus());
        assertEquals(true, ifOutOfId2);
    }

    @Test
    public void should_get_movie_list_in_movie_file() {
        assertEquals(2, movies.size());

        Movie eightMile = movies.get(0);
        Movie beatifulMind = movies.get(1);

        assertEquals("8 Mile", eightMile.getName());
        assertEquals(2002, eightMile.getYear());
        assertEquals("Curtis Hanson", eightMile.getDirector());
        assertEquals(2, eightMile.getRating());
        assertEquals("in", eightMile.getStatus());

        assertEquals("A Beautiful Mind", beatifulMind.getName());
        assertEquals(2001, beatifulMind.getYear());
        assertEquals("Ronald William Howard", beatifulMind.getDirector());
        assertEquals(1, beatifulMind.getRating());
        assertEquals("in", beatifulMind.getStatus());
    }

    @Test
    public void should_set_movie_status_out_when_check_out_a_movie() {
        boolean ifInOfId1 = bibliotecaApp.checkOutMovieWithName("8 Mile");
        boolean ifInOfId2 = bibliotecaApp.checkOutMovieWithName("TDD");

        assertEquals("out", movies.get(0).getStatus());
        assertEquals(true, ifInOfId1);
        assertEquals(false, ifInOfId2);
    }

    @Test
    public void should_return_in_when_user_not_log_in() throws Exception {
        String showLogContent = bibliotecaApp.getInOrOutByUser(null);
        assertEquals("in", showLogContent);
    }

    @Test
    public void should_return_out_when_user_logged_in() throws Exception {
        User user = new User("000-0000", "password", "name", "email", "number");
        String showLogContent = bibliotecaApp.getInOrOutByUser(user);
        assertEquals("out", showLogContent);
    }

    @Test
    public void should_return_null_if_username_or_password_is_wrong() throws Exception {
        List<String> userLineList = new ArrayList<String>();
        userLineList.add("000-0000    lilypassword    Lily    lily@thoughtworks.com    01066666666");
        User userWrongPassword = bibliotecaApp.getUserByLoginInfo("000-0000", "pass", userLineList);
        assertEquals(null, userWrongPassword);
        User userWrongName = bibliotecaApp.getUserByLoginInfo("000-0900", "lilypassword", userLineList);
        assertEquals(null, userWrongName);
    }

    @Test
    public void should_return_user_if_username_and_password_are_right() throws Exception {
        List<String> userLineList = new ArrayList<String>();
        userLineList.add("000-0000    lilypassword    Lily    lily@thoughtworks.com    01066666666");
        User user = bibliotecaApp.getUserByLoginInfo("000-0000", "lilypassword", userLineList);
        assertEquals("Lily", user.getName());
    }
}
