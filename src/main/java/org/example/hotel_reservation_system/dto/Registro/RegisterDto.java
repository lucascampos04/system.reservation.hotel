package org.example.hotel_reservation_system.dto.Registro;

import lombok.Data;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.Register.RegisterEntity}
 */
@Data
public class RegisterDto implements Serializable {
    Long Id;
    LocalDateTime inicio_checkin;
    LocalDateTime final_checkin;
    String forma_pagamento;
    StatusEnum status;
    Double valor;
    String descricao;
    LocalDateTime data_registro;
    ClientesEntity clientesEntity;
    EmployeesEntity employeesEntity;
}