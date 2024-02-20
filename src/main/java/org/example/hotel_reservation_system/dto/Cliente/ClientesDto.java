package org.example.hotel_reservation_system.dto.Cliente;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.Clientes.ClientesEntity}
 */
@Value
@Getter
@Setter
public class ClientesDto implements Serializable {
    Long Id;
    String nome;
    String email;
    String cpf;
    String rg;
    String endereco;
    String cep;
    String numero;
    String estado;
    String pais;
    StatusEnum status;
    LocalDateTime data_registro;
    String data_nascimento;

    public ClientesDto(Long id,
                       String nome,
                       String email,
                       String cpf,
                       String rg,
                       String endereco,
                       String cep,
                       String data_nascimento,
                       String numero,
                       String estado,
                       String pais,
                       StatusEnum status,
                       LocalDateTime data_registro) {
        Id = id;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.cep = cep;
        this.numero = numero;
        this.estado = estado;
        this.pais = pais;
        this.status = status;
        this.data_registro = data_registro;
    }
}