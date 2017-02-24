package com.twu.biblioteca;


import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void should_get_book_list_in_library_file() throws IOException {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        List<Book> books = bibliotecaApp.getBookList("test/com/twu/biblioteca/test_lib.txt");

        assertEquals(2, books.size());

        Book refactor = books.get(0);
        Book tdd = books.get(1);

        assertEquals("Refactor", refactor.getTitle());
        assertEquals("2003", refactor.getYear());
        assertEquals("MartinFowler", refactor.getAuthor());

        assertEquals("TDD", tdd.getTitle());
        assertEquals("2003", tdd.getYear());
        assertEquals("Beck", tdd.getAuthor());
    }
}
