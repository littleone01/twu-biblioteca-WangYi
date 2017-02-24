package com.twu.biblioteca;


import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void should_get_book_list_in_library_file() throws IOException {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        List<String> books = bibliotecaApp.getBookList("test/com/twu/biblioteca/test_lib.txt");
        assertEquals(2, books.size());
        assertEquals("Refactor    2003    MartinFowler", books.get(0));
        assertEquals("TDD    2003    Beck", books.get(1));
    }

    @Test
    public void should_get_book_details() throws Exception {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        List<String> books = bibliotecaApp.getBookList("test/com/twu/biblioteca/test_lib.txt");
        String[] detail1 = bibliotecaApp.getBookDetails(books.get(0));
        String[] detail2 = bibliotecaApp.getBookDetails(books.get(1));

        assertEquals("Refactor", detail1[0]);
        assertEquals("2003", detail1[1]);
        assertEquals("MartinFowler", detail1[2]);

        assertEquals("TDD", detail2[0]);
        assertEquals("2003", detail2[1]);
        assertEquals("Beck", detail2[2]);
    }


}
