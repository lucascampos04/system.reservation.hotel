package org.example.hotel_reservation_system.model.DadosLogin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dados_login")
public class DadosLogin {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "typeAcess")
    private String TypeAcess;

    @Column(name = "idCliente")
    private Long idCliente;


}