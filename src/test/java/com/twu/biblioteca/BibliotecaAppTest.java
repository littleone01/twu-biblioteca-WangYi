package com.twu.biblioteca;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.mock;


import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    @Test
    public void should_get_book_list_in_library_file() throws IOException {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.getBookList("src/test/java/com/twu/biblioteca/test_lib.txt");
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

    @Test
    public void should_set_book_status_out_when_check_out_a_book() throws Exception {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.getBookList("src/test/java/com/twu/biblioteca/test_lib.txt");
        List<Book> books = bibliotecaApp.getBooks();

        boolean ifInOfId1 = bibliotecaApp.checkOutBookWithTitle("Refactor");
        boolean ifInOfId2 = bibliotecaApp.checkOutBookWithTitle("TDD");

        assertEquals("out", books.get(0).getStatus());
        assertEquals(true, ifInOfId1);
        assertEquals(false, ifInOfId2);
    }

    @Test
    public void should_set_book_status_in_when_return_a_book() throws Exception {
        BibliotecaApp bibliotecaApp = new BibliotecaApp();
        bibliotecaApp.getBookList("src/test/java/com/twu/biblioteca/test_lib.txt");
        List<Book> books = bibliotecaApp.getBooks();
        boolean ifOutOfId2 = bibliotecaApp.returnBookWithTitle("TDD");

        assertEquals("in", books.get(1).getStatus());
        assertEquals(true, ifOutOfId2);
    }
}
