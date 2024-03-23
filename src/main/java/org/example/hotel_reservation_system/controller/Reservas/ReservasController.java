package org.example.hotel_reservation_system.controller.Reservas;

import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.services.ReservasServices.Get.ListAllReservasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservasController {
    @Autowired
    private ListAllReservasServices listAllClientes;
    @GetMapping("/get/list/all/reservas")
    public ResponseEntity<List<ReservasDto>> list(){
        List<ReservasDto> reservasDto = listAllClientes.listAllReservas();
        return ResponseEntity.ok().body(reservasDto);
    }
}
