package com.web.book.exception;

import java.util.UUID;

public class BookNotFoundException extends ResourceNotFoundException {

    public BookNotFoundException(UUID id) {
        super("Book not found with id: " + id);
    }
}
