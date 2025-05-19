package org.example.backend_exam.services.impl;

import org.example.backend_exam.dtos.ClientDTO;
import org.example.backend_exam.entities.Client;
import org.example.backend_exam.mappers.ClientMapper;
import org.example.backend_exam.repositories.ClientRepository;
import org.example.backend_exam.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
    
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }
    
    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }
    
    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        return clientMapper.toDto(client);
    }
    
    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toDto(savedClient);
    }
    
    @Override
    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        // Check if client exists
        clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        
        // Set the ID to ensure we're updating the existing client
        clientDTO.setId(id);
        Client client = clientMapper.toEntity(clientDTO);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.toDto(updatedClient);
    }
    
    @Override
    public void deleteClient(Long id) {
        // Check if client exists
        clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
        
        clientRepository.deleteById(id);
    }
}