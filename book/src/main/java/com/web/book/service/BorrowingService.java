package com.web.book.service;

import com.web.book.dto.BorrowingDto;
import com.web.book.dto.BorrowingReportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BorrowingService {

    void borrow(BorrowingDto dto);

    void returnBook(UUID id);

    Page<BorrowingReportDto> findAllReport(Pageable pageable);
}
