package org.example.hotel_reservation_system.controller.Clientes;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.services.AddClient.AddClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final AddClienteService addClienteService;
    public ClienteController(AddClienteService addClienteService) {
        this.addClienteService = addClienteService;
    }
    @PostMapping("/add/clientes")

    public ResponseEntity<String> cadastrarCliente(@RequestBody ClientesDto clientesDto){
        try{
            return addClienteService.AdicionarCliente(clientesDto);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
