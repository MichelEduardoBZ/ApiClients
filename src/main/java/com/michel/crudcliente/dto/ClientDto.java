package com.michel.crudcliente.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.michel.crudcliente.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ClientDto {

    private Long id;

    @NotBlank
    @Size(min = 3, max = 80)
    private String name;

    @NotBlank(message = "O CPF contém 11 dígitos")
    @Size(min = 11, max = 11)
    private String cpf;

    private double income;

    @NotNull
    @JsonProperty("birth_date")

    @PastOrPresent
    private LocalDate birthDate;

    private Integer children;

    public ClientDto(Client client) {
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }
}
