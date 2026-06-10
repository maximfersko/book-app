package com.web.book.service.impl;

import com.web.book.dto.BorrowingDto;
import com.web.book.dto.BorrowingReportDto;
import com.web.book.exception.BookAlreadyBorrowedException;
import com.web.book.exception.BookAlreadyReturnedException;
import com.web.book.exception.BookNotFoundException;
import com.web.book.exception.BorrowingNotFoundException;
import com.web.book.exception.ClientNotFoundException;
import com.web.book.mapper.BorrowingMapper;
import com.web.book.model.Book;
import com.web.book.model.Borrowing;
import com.web.book.model.Client;
import com.web.book.repository.BookRepository;
import com.web.book.repository.BorrowingRepository;
import com.web.book.repository.ClientRepository;
import com.web.book.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository borrowingRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final BorrowingMapper borrowingMapper;

    @Override
    @Transactional
    public void borrow(BorrowingDto dto) {
        Client client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ClientNotFoundException(dto.getClientId()));
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new BookNotFoundException(dto.getBookId()));

        if (borrowingRepository.existsActiveBorrowingByClientAndBook(dto.getClientId(), dto.getBookId())) {
            throw new BookAlreadyBorrowedException();
        }

        Borrowing borrowing = new Borrowing();
        borrowing.setClient(client);
        borrowing.setBook(book);
        borrowing.setBorrowDate(LocalDate.now());
        borrowingRepository.save(borrowing);
    }

    @Override
    @Transactional
    public void returnBook(UUID id) {
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new BorrowingNotFoundException(id));
        if (borrowing.getReturnDate() != null) {
            throw new BookAlreadyReturnedException(id);
        }
        borrowing.setReturnDate(LocalDate.now());
        borrowingRepository.save(borrowing);
    }

    @Override
    public Page<BorrowingReportDto> findAllReport(Pageable pageable) {
        return borrowingRepository.findAllActive(pageable)
                .map(borrowingMapper::toReportDto);
    }
}
