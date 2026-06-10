package com.web.book.service;

import com.web.book.dto.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClientService {

    Page<ClientDto> findAll(Pageable pageable);

    ClientDto findById(UUID id);

    ClientDto create(ClientDto dto);

    ClientDto update(UUID id, ClientDto dto);

    void delete(UUID id);
}
