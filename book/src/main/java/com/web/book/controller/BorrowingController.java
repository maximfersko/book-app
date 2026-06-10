package com.web.book.controller;

import com.web.book.dto.BorrowingDto;
import com.web.book.service.BookService;
import com.web.book.service.BorrowingService;
import com.web.book.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final BookService bookService;
    private final ClientService clientService;

    @Value("${app.page-size:20}")
    private int pageSize;

    @GetMapping
    public String list(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("page", borrowingService.findAllReport(
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "borrowDate"))));
        return "borrowings/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("borrowing", new BorrowingDto());
        model.addAttribute("books", bookService.findAll(Pageable.unpaged()).getContent());
        model.addAttribute("clients", clientService.findAll(Pageable.unpaged()).getContent());
        return "borrowings/form";
    }

    @PostMapping
    public String borrow(@Valid @ModelAttribute("borrowing") BorrowingDto dto,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("books", bookService.findAll(Pageable.unpaged()).getContent());
            model.addAttribute("clients", clientService.findAll(Pageable.unpaged()).getContent());
            return "borrowings/form";
        }
        borrowingService.borrow(dto);
        return "redirect:/borrowings/new?success";
    }

    @PostMapping("/{id}/return")
    public String returnBook(@PathVariable UUID id) {
        borrowingService.returnBook(id);
        return "redirect:/borrowings";
    }
}
