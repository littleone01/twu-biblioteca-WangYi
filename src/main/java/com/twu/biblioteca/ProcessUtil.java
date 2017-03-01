package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yibwang on 3/2/17.
 */
public class ProcessUtil {
    public static List<String> generateBookFileContext(BibliotecaApp bibliotecaApp) {
        List<String> bookFileContext = new ArrayList<String>();
        for (Book book : bibliotecaApp.getBooks()) {
            bookFileContext.add(book.outputLine());
        }
        return bookFileContext;
    }

    public static boolean checkOutBookWithTitle(String title, BibliotecaApp bibliotecaApp) {
        boolean ifIn = false;
        for (Book book : bibliotecaApp.getBooks()) {
            ifIn = book.CheckOut(title, bibliotecaApp.getUser());
            if (ifIn) {
                break;
            }
        }
        return ifIn;
    }
}
