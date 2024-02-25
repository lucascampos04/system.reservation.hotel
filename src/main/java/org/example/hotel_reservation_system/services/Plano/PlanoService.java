package org.example.hotel_reservation_system.services.Plano;

import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.dto.Plano.PlanoDto;
import org.springframework.stereotype.Service;

@Service
public class PlanoService {

    /*
    * Desconto em planos mais basicos
    * */
    public void isDescontoPlanos(ClientesDto clientesDto, PlanoDto planoDto) {
        if (clientesDto.getRole() == RolesEnum.ROLE_FUNCIONARIO_GERENCIA ||
                clientesDto.getRole() == RolesEnum.ROLE_CLIENTE_EXECUTIVO ||
                clientesDto.getRole() == RolesEnum.ROLE_CLIENTE_LUXO) {
            Double desconto = 10.99;
            Double novoValor = planoDto.getValor() * (1 - desconto);
            planoDto.setValor(novoValor);
        }

        if (clientesDto.getRole() == RolesEnum.ROLE_ADMINISTRADOR_HOTEL){
            Double desconto = 5.99;
            Double novoValor = planoDto.getValor() * (1 - desconto);
            planoDto.setValor(novoValor);
        }
    }
}
