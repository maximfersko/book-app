package com.web.book.mapper;

import com.web.book.dto.BorrowingReportDto;
import com.web.book.model.Borrowing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowingMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "client.fullName", target = "fullName")
    @Mapping(source = "client.birthDate", target = "birthDate")
    @Mapping(source = "book.title", target = "bookTitle")
    @Mapping(source = "book.author", target = "author")
    @Mapping(source = "book.isbn", target = "isbn")
    @Mapping(source = "returnDate", target = "returnDate")
    BorrowingReportDto toReportDto(Borrowing borrowing);
}
