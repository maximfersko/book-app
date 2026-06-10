package com.web.book.exception;

public class IsbnAlreadyExistsException extends AlreadyExistsException {

    public IsbnAlreadyExistsException(String isbn) {
        super("Book with ISBN " + isbn + " already exists");
    }
}
