package org.example.hotel_reservation_system.ServiceTest;

import org.example.hotel_reservation_system.Enum.roles.RolesEnum;
import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.dto.Registro.RegisterDto;
import org.example.hotel_reservation_system.services.Plano.PlanoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlanoServiceTest {
    @Test
    void testeIsDesconto(){
        PlanoService planoService = new PlanoService();
        ClientesDto clientesDto = new ClientesDto();

        clientesDto.setRole(RolesEnum.ROLE_CLIENTE_EXECUTIVO);

        RegisterDto registerDto = new RegisterDto();
        registerDto.setValor(100.0);

        planoService.isDescontoPlanos(clientesDto, registerDto);

        assertEquals(70.0, registerDto.getValor(),  0.0001);
        assertTrue(registerDto.getValor() > 0);
        System.out.println("Teste bem sucedido: Desconto funcionou corretamente");
    }
}