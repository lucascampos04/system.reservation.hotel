package org.example.hotel_reservation_system.controller.Clientes;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.services.Cliente.Get.ListAll.ListAllClientesServices;
import org.example.hotel_reservation_system.services.Cliente.Put.PutCliente;
import org.example.hotel_reservation_system.services.Cliente.Post.AddClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {
    private final AddClienteService addClienteService;
    private final PutCliente putCliente;
    private final ListAllClientesServices listAllClientesServices;
    public ClienteController(AddClienteService addClienteService, PutCliente putCliente, ListAllClientesServices listAllClientesServices) {
        this.addClienteService = addClienteService;
        this.putCliente = putCliente;
        this.listAllClientesServices = listAllClientesServices;
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
    @PutMapping("/update/put/clientes/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestBody ClientesDto clientesDto){
        try {
            return putCliente.updateClientes(id, clientesDto);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao atualizar cliente");
        }
    }

    @PatchMapping("/update/patch/clientes/{id}")
    public ResponseEntity<String> atualizarClienteParcial(@PathVariable Long id, @RequestBody ClientesDto clientes){
        try {
           return ResponseEntity.ok("Cliente Atualizado com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao atualizar cliente");
        }
    }

    @GetMapping("/get/all/clientes")
    public ResponseEntity<List<ClientesDto>> list(){
        List<ClientesDto> clientesDtos = listAllClientesServices.listAllClientes();
        return ResponseEntity.ok().body(clientesDtos);
    }
}
