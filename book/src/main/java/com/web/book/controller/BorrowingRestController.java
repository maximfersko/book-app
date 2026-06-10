package com.web.book.controller;

import com.web.book.dto.BorrowingReportDto;
import com.web.book.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrowings")
@RequiredArgsConstructor
public class BorrowingRestController {

    private final BorrowingService borrowingService;

    @Value("${app.page-size:20}")
    private int pageSize;

    @GetMapping
    public Page<BorrowingReportDto> findAll(@RequestParam(defaultValue = "0") int page) {
        return borrowingService.findAllReport(
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "borrowDate")));
    }
}
