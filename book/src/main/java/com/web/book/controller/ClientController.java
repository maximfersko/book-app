package com.web.book.controller;

import com.web.book.dto.ClientDto;
import com.web.book.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @Value("${app.page-size:20}")
    private int pageSize;

    @GetMapping
    public String list(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("page", clientService.findAll(PageRequest.of(page, pageSize)));
        return "clients/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("client", new ClientDto());
        return "clients/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("client") ClientDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "clients/form";
        }
        clientService.create(dto);
        return "redirect:/clients";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable UUID id, Model model) {
        model.addAttribute("client", clientService.findById(id));
        return "clients/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable UUID id,
                         @Valid @ModelAttribute("client") ClientDto dto,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "clients/form";
        }
        clientService.update(id, dto);
        return "redirect:/clients";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable UUID id) {
        clientService.delete(id);
        return "redirect:/clients";
    }
}
