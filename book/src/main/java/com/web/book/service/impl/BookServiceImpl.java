package com.web.book.service.impl;

import com.web.book.dto.BookDto;
import com.web.book.exception.BookHasActiveBorrowingsException;
import com.web.book.exception.BookNotFoundException;
import com.web.book.mapper.BookMapper;
import com.web.book.model.Book;
import com.web.book.repository.BookRepository;
import com.web.book.repository.BorrowingRepository;
import com.web.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BorrowingRepository borrowingRepository;
    private final BookMapper bookMapper;

    @Override
    public Page<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).map(bookMapper::toDto);
    }

    @Override
    public BookDto findById(UUID id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public BookDto create(BookDto dto) {
        Book saved = bookRepository.save(bookMapper.toEntity(dto));
        return bookMapper.toDto(saved);
    }

    @Override
    @Transactional
    public BookDto update(UUID id, BookDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        book.setAuthor(dto.getAuthor());
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        Book saved = bookRepository.save(book);
        return bookMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }
        if (borrowingRepository.existsActiveBorrowingByBook(id)) {
            throw new BookHasActiveBorrowingsException();
        }
        bookRepository.deleteById(id);
    }
}
