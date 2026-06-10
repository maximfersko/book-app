package com.web.book.mapper;

import com.web.book.dto.ClientDto;
import com.web.book.model.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto toDto(Client client);

    Client toEntity(ClientDto dto);
}
