package com.web.book.controller;

import com.web.book.dto.BookDto;
import com.web.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String list(Model model, @PageableDefault(size = 20) Pageable pageable) {
        model.addAttribute("page", bookService.findAll(pageable));
        return "books/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("book", new BookDto());
        return "books/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("book") BookDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.create(dto);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "books/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable UUID id,
                         @Valid @ModelAttribute("book") BookDto dto,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "books/form";
        }
        bookService.update(id, dto);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
