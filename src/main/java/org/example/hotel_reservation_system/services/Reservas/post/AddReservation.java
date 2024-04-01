package org.example.hotel_reservation_system.services.Reservas.post;

import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.example.hotel_reservation_system.repository.Reservas.ReservasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddReservation {
    private final ReservasRepository reservasRepository;
    public AddReservation(ReservasRepository reservasRepository) {
        this.reservasRepository = reservasRepository;
    }

    public ResponseEntity<String> addReservation(ReservasDto reservasDto) {
        try {
            ReservasEntity reservasEntity = getDads(reservasDto);
            reservasRepository.save(reservasEntity);
            return ResponseEntity.ok("Reserva adicionada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao adicionar reserva");
        }
    }

    private ReservasEntity getDads(ReservasDto reservasDto) {
        ReservasEntity reservasEntity = new ReservasEntity();
        reservasEntity.setPackageName(reservasDto.getPackageName());
        return reservasEntity;
    }
}
