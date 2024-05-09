package org.example.hotel_reservation_system.Model.Users;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "employee_data")
public class EmployeeData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "position")
    private String position;

    @OneToOne
    @JoinColumn(name = "employee_id")
    private UsersEntity employee_id;
}
