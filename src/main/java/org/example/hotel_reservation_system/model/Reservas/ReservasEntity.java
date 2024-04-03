package org.example.hotel_reservation_system.model.Reservas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
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
    @Enumerated(EnumType.STRING)
    private PacoteEnum packageName;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "valor_sem_desconto")
    private Double valorSemDesconto;

    @Column(name = "desconto_aplicado")
    private String descontoAplicado;

    @Column(name = "data_checkin")
    private LocalDateTime data_checkin;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToOne
    private ClientesEntity cliente;
}