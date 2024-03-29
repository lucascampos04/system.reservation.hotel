package org.example.hotel_reservation_system.dto.Reservas;

import lombok.Value;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;

import java.io.Serializable;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.Reservas.ReservasEntity}
 */
@Value
public class ReservasDto implements Serializable {
    Long id;
    String packageName;

    public static ReservasDto fromEntity(ReservasEntity entity){
        return new ReservasDto(
                entity.getId(),
                entity.getPackageName()
        );
    }

    public ReservasDto(Long id, String packageName) {
        this.id = id;
        this.packageName = packageName;
    }
}