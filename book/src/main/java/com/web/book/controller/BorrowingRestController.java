package com.web.book.controller;

import com.web.book.dto.BorrowingReportDto;
import com.web.book.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/borrowings")
@RequiredArgsConstructor
public class BorrowingRestController {

    private final BorrowingService borrowingService;

    @GetMapping
    public Page<BorrowingReportDto> findAll(
            @PageableDefault(size = 20, sort = "borrowDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return borrowingService.findAllReport(pageable);
    }
}
