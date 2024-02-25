package org.example.hotel_reservation_system.services.Plano;

import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.dto.Registro.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public class PlanoService {

    /*
    * Desconto em planos mais basicos
    * */
    public void isDescontoPlanos(ClientesDto clientesDto, RegisterDto registerDto) {
        double isDescontoTrinta = 0.3;
        double isDescontoDez = 0.1;

        if (clientesDto.getRole() == RolesEnum.ROLE_FUNCIONARIO_GERENCIA ||
                clientesDto.getRole() == RolesEnum.ROLE_CLIENTE_EXECUTIVO ||
                clientesDto.getRole() == RolesEnum.ROLE_CLIENTE_LUXO) {
            Double desconto = registerDto.getValor() * isDescontoTrinta;
            Double novoValor = registerDto.getValor() - desconto;
            registerDto.setValor(novoValor);
        }

        if (clientesDto.getRole() == RolesEnum.ROLE_ADMINISTRADOR_HOTEL){
            Double desconto = registerDto.getValor() * isDescontoDez;
            Double novoValor = registerDto.getValor() - desconto;
            registerDto.setValor(novoValor);
        }
    }
}
