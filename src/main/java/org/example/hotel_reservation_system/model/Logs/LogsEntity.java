package org.example.hotel_reservation_system.model.Logs;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "logs")
public class LogsEntity {
    @Id
    private Long id;

    @Column(name = "login", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime login;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private ClientesEntity clienteId;

    @ManyToOne
    @JoinColumn(name = "employees_id", referencedColumnName = "id")
    private EmployeesEntity employeesId;
}
