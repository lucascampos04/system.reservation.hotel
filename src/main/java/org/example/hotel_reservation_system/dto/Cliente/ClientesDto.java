package org.example.hotel_reservation_system.dto.Cliente;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Plano.PlanoEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class ClientesDto implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String cpf;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String rg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String endereco;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String cep;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String numero;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String estado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String pais;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    StatusEnum status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime data_registro;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String data_nascimento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    RolesEnum role;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Double planoValor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String plano;

    private Map<String, String> updates;

    public ClientesDto() {}

    public static ClientesDto fromEntity(ClientesEntity entity) {
        if (entity == null) {
            return null;
        }

        PlanoEntity plano = entity.getPlano();
        Double planoValor = (plano != null) ? plano.getValor() : null;
        String planoNome = (plano != null) ? String.valueOf(plano.getPlano()) : null;
        if (entity.getStatus().equals(StatusEnum.INATIVO) || entity.getStatus().equals(StatusEnum.DESATIVADO)){
            return new ClientesDto(
                    entity.getId(),
                    entity.getNome(),
                    entity.getStatus()
            );
        } else {
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

    public ClientesDto(Long id, String nome, StatusEnum status) {
        this.id = id;
        this.nome = nome;
        this.status = status;
    }

    public Map<String, String> getUpdates() {
        return updates;
    }

    public void addUpdate(String columnName, String newValue) {
        updates.put(columnName, newValue);
    }
}
