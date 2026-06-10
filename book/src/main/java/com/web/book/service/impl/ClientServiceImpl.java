package com.web.book.service.impl;

import com.web.book.dto.ClientDto;
import com.web.book.exception.ClientHasActiveBorrowingsException;
import com.web.book.exception.ClientNotFoundException;
import com.web.book.mapper.ClientMapper;
import com.web.book.model.Client;
import com.web.book.repository.BorrowingRepository;
import com.web.book.repository.ClientRepository;
import com.web.book.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final BorrowingRepository borrowingRepository;
    private final ClientMapper clientMapper;

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::toDto);
    }

    @Override
    public ClientDto findById(UUID id) {
        return clientRepository.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @Override
    public ClientDto create(ClientDto dto) {
        Client saved = clientRepository.save(clientMapper.toEntity(dto));
        return clientMapper.toDto(saved);
    }

    @Override
    @Transactional
    public ClientDto update(UUID id, ClientDto dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
        client.setFullName(dto.getFullName());
        client.setBirthDate(dto.getBirthDate());
        Client saved = clientRepository.save(client);
        return clientMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException(id);
        }
        if (borrowingRepository.existsActiveBorrowingByClient(id)) {
            throw new ClientHasActiveBorrowingsException();
        }
        clientRepository.deleteById(id);
    }
}
