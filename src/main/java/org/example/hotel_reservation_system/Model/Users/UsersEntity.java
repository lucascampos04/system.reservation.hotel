package org.example.hotel_reservation_system.Model.Users;

import jakarta.persistence.*;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "type_user")
    private String typeUser;

    @OneToOne(
            mappedBy = "usersFk",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private UserData userData;

    @OneToOne(
            mappedBy = "employee_id",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private EmployeeData employeeData;

}
