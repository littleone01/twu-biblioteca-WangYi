package com.twu.biblioteca;


import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void should_get_book_list_in_library_file() throws IOException {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.getBookList("test/com/twu/biblioteca/test_lib.txt");
        List<Book> books = bibliotecaApp.getBooks();

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
        assertEquals("out", "Beck", tdd.getAuthor());
    }
}
