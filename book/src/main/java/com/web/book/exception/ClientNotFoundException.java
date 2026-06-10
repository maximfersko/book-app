package com.web.book.exception;

import java.util.UUID;

public class ClientNotFoundException extends ResourceNotFoundException {

    public ClientNotFoundException(UUID id) {
        super("Client not found with id: " + id);
    }
}
