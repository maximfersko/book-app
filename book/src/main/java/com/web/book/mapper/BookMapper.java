package com.web.book.mapper;

import com.web.book.dto.BookDto;
import com.web.book.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookDto dto);
}
