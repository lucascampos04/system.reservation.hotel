package org.example.hotel_reservation_system.controller.Reservas;

import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.services.Reservas.get.ListAllReservas;
import org.example.hotel_reservation_system.services.Reservas.post.AddReservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservasController {

    private final ListAllReservas listAllReservas;
    private final AddReservation addReservation;
    public ReservasController(ListAllReservas listAllReservas, AddReservation addReservation) {
        this.listAllReservas = listAllReservas;
        this.addReservation = addReservation;
    }

    @PostMapping("/post/add/reserva")
    public String cadastrarReserva(@RequestBody ReservasDto reservasDto) {
        try {
            addReservation.addReservation(reservasDto);
            return "Reserva adicionada com sucesso";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao adicionar reserva";
        }
    }

    @GetMapping("/get/list/reservas")
    public ResponseEntity<List<ReservasDto>> list(){
        List<ReservasDto> reservasDtos = listAllReservas.listAllReservas();
        return ResponseEntity.ok(reservasDtos);
    }
}
