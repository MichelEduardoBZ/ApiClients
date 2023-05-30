package com.michel.crudcliente.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tb_client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpf;

    private double income;

    @Column(name = "birth_date")

    private LocalDate birthDate;

    private Integer children;

}
