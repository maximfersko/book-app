package com.web.book.exception;

import java.util.UUID;

public class BookAlreadyReturnedException extends AppException {

    public BookAlreadyReturnedException(UUID id) {
        super("Book already returned for borrowing: " + id);
    }
}
