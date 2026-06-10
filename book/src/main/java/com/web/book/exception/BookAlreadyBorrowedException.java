package com.web.book.exception;

public class BookAlreadyBorrowedException extends AppException {

    public BookAlreadyBorrowedException() {
        super("Client already has an active borrowing for this book");
    }
}
