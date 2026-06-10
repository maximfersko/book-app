package com.web.book.exception;

import java.util.UUID;

public class BorrowingNotFoundException extends ResourceNotFoundException {

    public BorrowingNotFoundException(UUID id) {
        super("Borrowing not found with id: " + id);
    }
}
