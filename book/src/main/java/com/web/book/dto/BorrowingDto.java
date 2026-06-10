package com.web.book.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
public class BorrowingDto {

    @NotNull
    private UUID clientId;

    @NotNull
    private UUID bookId;
}
