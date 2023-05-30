package com.michel.crudcliente.controllers;

import com.michel.crudcliente.dto.ClientDto;
import com.michel.crudcliente.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@Valid @RequestBody ClientDto clientDto) {
        clientService.addClient(clientDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(clientDto.getId()).toUri();
        return ResponseEntity.created(uri).body(clientDto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> showClients(Pageable pageable) {
        Page<ClientDto> clientDtos = clientService.showClients(pageable);
        return ResponseEntity.ok(clientDtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> showClientById(@PathVariable Long id) {
        ClientDto clientDto = clientService.showClientById(id);
        return ResponseEntity.ok(clientDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteClientById(@PathVariable Long id) {
        clientService.deleteClientById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDto clientDto) {
        clientDto = clientService.updateClient(id, clientDto);
        return ResponseEntity.ok(clientDto);
    }

}
