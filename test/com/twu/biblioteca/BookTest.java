package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by yibwang on 2/24/17.
 */
public class BookTest {
    @Test
    public void should_return_length_of_int_value() throws Exception {
        Book book = new Book(12, "title", 2003, "author");
        int idLength = book.getIdLength(book.getId());
        assertEquals(2, idLength);
    }
}
