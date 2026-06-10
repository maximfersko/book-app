package com.web.book.repository;

import com.web.book.model.Borrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface BorrowingRepository extends JpaRepository<Borrowing, UUID> {

    @Query("SELECT COUNT(b) > 0 FROM Borrowing b WHERE b.client.id = :clientId AND b.book.id = :bookId AND b.returnDate IS NULL")
    boolean existsActiveBorrowingByClientAndBook(@Param("clientId") UUID clientId, @Param("bookId") UUID bookId);

    @Query("SELECT COUNT(b) > 0 FROM Borrowing b WHERE b.client.id = :clientId AND b.returnDate IS NULL")
    boolean existsActiveBorrowingByClient(@Param("clientId") UUID clientId);

    @Query("SELECT COUNT(b) > 0 FROM Borrowing b WHERE b.book.id = :bookId AND b.returnDate IS NULL")
    boolean existsActiveBorrowingByBook(@Param("bookId") UUID bookId);

    @Query(value = "SELECT b FROM Borrowing b JOIN FETCH b.client JOIN FETCH b.book WHERE b.returnDate IS NULL",
           countQuery = "SELECT COUNT(b) FROM Borrowing b WHERE b.returnDate IS NULL")
    Page<Borrowing> findAllActive(Pageable pageable);

    @Query(value = "SELECT b FROM Borrowing b JOIN FETCH b.client JOIN FETCH b.book",
           countQuery = "SELECT COUNT(b) FROM Borrowing b")
    Page<Borrowing> findAllWithClientAndBook(Pageable pageable);
}
