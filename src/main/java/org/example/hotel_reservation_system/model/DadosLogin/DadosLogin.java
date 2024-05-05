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
public class DadosLogin{
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "type_acess")
    private String typeAccess;

    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "type_login")
    private String typeLogin;

}
