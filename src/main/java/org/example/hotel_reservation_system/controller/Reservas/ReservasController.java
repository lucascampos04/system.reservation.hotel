package org.example.hotel_reservation_system.controller.Reservas;

import lombok.Getter;
import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.services.Reservas.get.ListAllReservas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservasController {

    private final ListAllReservas listAllReservas;
    public ReservasController(ListAllReservas listAllReservas) {
        this.listAllReservas = listAllReservas;
    }

    @GetMapping("/get/list/reservas")
    public ResponseEntity<List<ReservasDto>> list(){
        List<ReservasDto> reservasDtos = listAllReservas.listAllReservas();
        return ResponseEntity.ok(reservasDtos);
    }
}
