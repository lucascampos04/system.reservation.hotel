package org.example.hotel_reservation_system.controller.Clientes;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.services.Cliente.Put.PutCliente;
import org.example.hotel_reservation_system.services.Cliente.Post.AddClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final AddClienteService addClienteService;
    private final PutCliente putCliente;
    public ClienteController(AddClienteService addClienteService, PutCliente putCliente) {
        this.addClienteService = addClienteService;
        this.putCliente = putCliente;
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
    @PutMapping("/upddate/patch/clientes/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestBody ClientesDto clientesDto){
        try {
            return putCliente.updateClientes(id, clientesDto);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao atualizar cliente");
        }
    }
}
