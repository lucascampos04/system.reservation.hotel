package org.example.hotel_reservation_system.services.patterns.RegisterAccount;

import org.example.hotel_reservation_system.repository.DadosLogin.DadosLoginRepository;
import org.springframework.stereotype.Service;

@Service
public class TypeAcessOfCreateAccount implements ITypeAcess{

    @Override
    public String getType(String text) {
        if (text.length() == 5){
            return "CLIENTE";
        } else if (text.length() == 7) {
            return "FUNCIONARIO";
        } else {
            throw new IllegalArgumentException("ERRO, TIPO DE LOGIN DESCONHECIO");
        }
    }
}
