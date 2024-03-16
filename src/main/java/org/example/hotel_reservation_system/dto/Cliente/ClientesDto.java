package org.example.hotel_reservation_system.dto.Cliente;

import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Plano.PlanoEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class ClientesDto implements Serializable {
    Long id;
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
    RolesEnum role;
    Double planoValor;
    String plano;

    public ClientesDto() {}

    public static ClientesDto fromEntity(ClientesEntity entity) {
        if (entity == null ) {
            return null;
        }

        PlanoEntity plano = entity.getPlano();
        Double planoValor = (plano != null) ? plano.getValor() : null;
        String planoNome = (plano != null) ? String.valueOf(plano.getPlano()) : null;

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
                entity.getData_registro(),
                entity.getRole(),
                planoValor,
                (entity.getStatus() == StatusEnum.ATIVO) ? planoValor : null,
                planoNome
        );
    }

    public ClientesDto(Long id, String nome, String email, String cpf, String rg, String endereco, String cep,
                       String data_nascimento, String numero, String estado, String pais, StatusEnum status,
                       LocalDateTime data_registro, RolesEnum role, Double planoValor, Double planoValorAtivo,
                       String plano) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.cep = cep;
        this.data_nascimento = data_nascimento;
        this.numero = numero;
        this.estado = estado;
        this.pais = pais;
        this.status = status;
        this.data_registro = data_registro;
        this.role = role;
        this.planoValor = planoValor;
        this.plano = plano;

        if (status == StatusEnum.ATIVO) {
            this.planoValor = planoValorAtivo;
        }
    }
}
