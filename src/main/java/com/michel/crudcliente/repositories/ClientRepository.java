package com.michel.crudcliente.repositories;

import com.michel.crudcliente.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface ClientRepository extends JpaRepository<Client, Long> {
}
