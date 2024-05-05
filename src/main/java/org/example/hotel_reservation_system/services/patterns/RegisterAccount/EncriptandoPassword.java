package org.example.hotel_reservation_system.services.patterns.RegisterAccount;

import org.example.hotel_reservation_system.services.patterns.PlansValues.MainAplicationValues;
import org.example.hotel_reservation_system.services.patterns.Regex.MainRegexApplication;
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
        MainRegexApplication.PasswordRegex passwordRegex = new MainRegexApplication.PasswordRegex();

        String passwordValidationMessage = passwordRegex.applyRegex(password);

        if (passwordValidationMessage != null) {
            throw new IllegalArgumentException(passwordValidationMessage);
        }

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
