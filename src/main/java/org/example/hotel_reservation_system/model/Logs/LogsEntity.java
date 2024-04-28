package org.example.hotel_reservation_system.model.Logs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "logs_entity")
public class LogsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "date_login")
    private LocalDateTime date_login;

    @ManyToOne
    private ClientesEntity clientesEntity;
}
