package org.example.hotel_reservation_system.services.patterns.RegisterAccount;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.model.DadosLogin.DadosLogin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncriptandoPassword implements IEncriptandoPassword {

    private final BCryptPasswordEncoder passwordEncoderCliente;
    private final BCryptPasswordEncoder passwordEncoderFuncionario;

    public EncriptandoPassword() {
        this.passwordEncoderCliente = new BCryptPasswordEncoder();
        this.passwordEncoderFuncionario = new BCryptPasswordEncoder();
    }

    @Override
    public String encriptarPassword(String password) {
        String typeAcessCliente = "CLIENTE";
        String typeAcessFuncionario = "FUNCIONARIO";

        BCryptPasswordEncoder encoder;

        if ("CLIENTE".equals(typeAcessCliente)) {
            encoder = passwordEncoderCliente;
        } else if ("FUNCIONARIO".equals(typeAcessCliente)) {
            encoder = passwordEncoderFuncionario;
        } else {
            throw new IllegalArgumentException("Tipo de acesso desconhecido: ");
        }
        return encoder.encode(password);
    }
}
