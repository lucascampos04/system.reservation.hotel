package org.example.hotel_reservation_system.model.Reservas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class ReservasEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "package_name")
    private PacoteEnum packageName;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data_checkin")
    private LocalDateTime data_checkin;

    @ManyToOne
    private ClientesEntity cliente;
}