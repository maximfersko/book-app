package com.web.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflict(AlreadyExistsException ex, Model model) {
        model.addAttribute("status", 409);
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler({
            BookAlreadyBorrowedException.class,
            ClientHasActiveBorrowingsException.class,
            BookHasActiveBorrowingsException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleBusinessConflict(AppException ex, Model model) {
        model.addAttribute("status", 409);
        model.addAttribute("message", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidation(MethodArgumentNotValidException ex, Model model) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .reduce((a, b) -> a + "; " + b)
                .orElse("Validation error");
        model.addAttribute("status", 400);
        model.addAttribute("message", message);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("status", 500);
        model.addAttribute("message", "Internal server error");
        return "error";
    }
}
