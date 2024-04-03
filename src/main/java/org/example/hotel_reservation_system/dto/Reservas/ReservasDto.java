package org.example.hotel_reservation_system.dto.Reservas;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.Reservas.ReservasEntity}
 */
@Data
public class ReservasDto implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    PacoteEnum packageName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime data_checkin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Double valor;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String nomeCliente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long idCliente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String emailCliente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TipoPlanoEnum planoCliente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RolesEnum roleCliente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusEnum status;


    public static ReservasDto fromEntity(ReservasEntity entity){
        if (entity.getCliente() != null) {
            return new ReservasDto(
                    entity.getId(),
                    entity.getPackageName(),
                    entity.getData_checkin(),
                    entity.getValor(),
                    entity.getCliente().getNome(),
                    entity.getCliente().getId(),
                    entity.getCliente().getEmail(),
                    entity.getCliente().getPlano().getPlano(),
                    entity.getCliente().getRole(),
                    entity.getStatus()
            );
        } else {
            return new ReservasDto(
                    entity.getId(),
                    entity.getPackageName(),
                    entity.getData_checkin(),
                    entity.getValor(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }
    }

    public ReservasDto(Long id, PacoteEnum packageName,
                       LocalDateTime data_checkin,
                       Double valor, String nomeCliente, Long idCliente, String emailCliente, TipoPlanoEnum planoCliente, RolesEnum role,
                       StatusEnum status) {
        this.id = id;
        this.packageName = packageName;
        this.data_checkin = data_checkin;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.idCliente = idCliente;
        this.emailCliente = emailCliente;
        this.planoCliente = planoCliente;
        this.roleCliente = role;
        this.status = status;
    }
}