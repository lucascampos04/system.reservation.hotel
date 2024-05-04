package org.example.hotel_reservation_system.dto.DadosLogin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.DadosLogin.DadosLogin}
 */
@Getter @Setter @AllArgsConstructor
public class DadosLoginDto implements Serializable {
    Long id;
    String login;
    String password;
    String TypeAcess;
    Long idCliente;
}