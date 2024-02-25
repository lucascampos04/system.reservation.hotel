package org.example.hotel_reservation_system.dto.Plano;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;
import org.example.hotel_reservation_system.model.Plano.PlanoEntity;

import java.io.Serializable;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.Plano.PlanoEntity}
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanoDto implements Serializable {
    TipoPlanoEnum plano;
    Double valor;

    public static PlanoDto fromEntity(PlanoEntity entity) {
        if (entity == null){
            return null;
        }
        PlanoDto dto = new PlanoDto();
        dto.setPlano(entity.getPlano());
        dto.setValor(entity.getValor());
        return dto;
    }
    public PlanoDto(TipoPlanoEnum plano, Double valor) {
        this.plano = plano;
        this.valor = valor;
    }

    public PlanoDto(){

    }
}