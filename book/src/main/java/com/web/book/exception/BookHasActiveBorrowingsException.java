package com.web.book.exception;

public class BookHasActiveBorrowingsException extends AppException {

    public BookHasActiveBorrowingsException() {
        super("Cannot delete book with active borrowings");
    }
}
