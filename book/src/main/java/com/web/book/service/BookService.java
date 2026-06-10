package com.web.book.service;

import com.web.book.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {

    Page<BookDto> findAll(Pageable pageable);

    BookDto findById(UUID id);

    BookDto create(BookDto dto);

    BookDto update(UUID id, BookDto dto);

    void delete(UUID id);
}
