package org.example.hotel_reservation_system.services.Cliente.Get.ListAll;

import org.example.hotel_reservation_system.dto.Cliente.ClientesDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllClientesServices {

    @Autowired
    private ClientesRepository clientesRepository;

    public List<ClientesDto> listAllClientes(){
        try {
            List<ClientesEntity> clients = clientesRepository.findAll();
            return clients.stream().map(ClientesDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
