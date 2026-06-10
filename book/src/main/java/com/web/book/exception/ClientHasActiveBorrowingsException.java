package com.web.book.exception;

public class ClientHasActiveBorrowingsException extends AppException {

    public ClientHasActiveBorrowingsException() {
        super("Cannot delete client with active borrowings");
    }
}
