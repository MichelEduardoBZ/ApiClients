package com.michel.crudcliente.services;

import com.michel.crudcliente.dto.ClientDto;
import com.michel.crudcliente.entities.Client;
import com.michel.crudcliente.repositories.ClientRepository;
import com.michel.crudcliente.services.exceptions.DatabaseException;
import com.michel.crudcliente.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public ClientDto addClient(ClientDto clientDto) {
        Client client = new Client();
        copyClientDtoToClient(client, clientDto);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> showClients(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(ClientDto::new);
    }

    @Transactional(readOnly = true)
    public ClientDto showClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente inexistente"));
        return new ClientDto(client);
    }


    @Transactional
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        try {
            Client client = clientRepository.getReferenceById(id);
            copyClientDtoToClient(client, clientDto);
            client = clientRepository.save(client);
            return new ClientDto(client);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Cliente inexistente");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteClientById(Long id) {
        if (!clientRepository.existsById(id))
            throw new ResourceNotFoundException("Cliente inexistente");
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyClientDtoToClient(Client client, ClientDto clientDto) {
        client.setName(clientDto.getName());
        client.setCpf(clientDto.getCpf());
        client.setIncome(clientDto.getIncome());
        client.setBirthDate(clientDto.getBirthDate());
        client.setChildren(client.getChildren());
    }
}
