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
        assertEquals("Refactor", books.get(0));
        assertEquals("TDD", books.get(1));
    }
}
