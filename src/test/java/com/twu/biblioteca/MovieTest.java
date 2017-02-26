package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by yibwang on 2/27/17.
 */
public class MovieTest {
    @Test
    public void should_return_a_standard_line_when_call_outputLine_method() throws Exception {
        Movie movie = new Movie("name", 2003, "director", 8, "in");
        String expected = "name    2003    director    8    in";
        assertEquals(expected, movie.outputLine());
    }
}
