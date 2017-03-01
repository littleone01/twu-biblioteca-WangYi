package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by yibwang on 2/24/17.
 */
public class BookTest {
    private Book bookIn;
    private Book bookOut;

    @Before
    public void setUp() throws Exception {
        bookIn = new Book(12, "title", 2003, "author", "in");
        bookOut = new Book(11, "outbook", 2002, "outauthor", "out");
        bookOut.setLenter("000-0000");
    }

    @Test
    public void should_return_length_of_int_value() throws Exception {
        int idLength = bookIn.getIdLength(bookIn.getId());
        assertEquals(2, idLength);
    }

    @Test
    public void should_return_a_standard_line_when_call_outputLine_method() throws Exception {
        String expectedIn = "12    title    2003    author    in";
        assertEquals(expectedIn, bookIn.outputLine());
        String expectedOut = "11    outbook    2002    outauthor    out    000-0000";
        assertEquals(expectedOut, bookOut.outputLine());
    }

    @Test
    public void should_return_true_if_book_status_is_in() throws Exception {
        boolean ifSuccess = bookIn.CheckOut("title", new User("0", "0", "0", "0", "0"));
        assertEquals(true, ifSuccess);
    }

    @Test
    public void should_return_false_if_book_status_is_out() throws Exception {
        boolean ifSuccess = bookIn.CheckOut("outbook", new User("0", "0", "0", "0", "0"));
        assertEquals(false, ifSuccess);
    }
}
