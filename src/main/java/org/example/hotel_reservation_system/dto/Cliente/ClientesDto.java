package org.example.hotel_reservation_system.dto.Cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.Exception.ClienteNaoAtivoException;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.Clientes.ClientesEntity}
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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

    public static ClientesDto fromEntity(ClientesEntity entity){
        if (entity.getStatus() == StatusEnum.ATIVO){
            return new ClientesDto(
                    entity.getId(),
                    entity.getNome(),
                    entity.getEmail(),
                    entity.getCpf(),
                    entity.getRg(),
                    entity.getEndereco(),
                    entity.getCep(),
                    entity.getData_nascimento(),
                    entity.getNumero(),
                    entity.getEstado(),
                    entity.getPais(),
                    entity.getStatus(),
                    entity.getData_registro()
            );
        } else {
            return new ClientesDto(entity.getId(), null, null, null, null, null, null, null, null, null, null, entity.getStatus(), null);
        }

    }

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