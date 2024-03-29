package org.example.hotel_reservation_system.services.Reservas.get;

import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.example.hotel_reservation_system.repository.Reservas.ReservasRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllReservas {

    private final ReservasRepository reservasRepository;
    public ListAllReservas(ReservasRepository reservasRepository) {
        this.reservasRepository = reservasRepository;
    }

    public List<ReservasDto> listAllReservas() {
        try{
            List<ReservasEntity> reservas = reservasRepository.findAll();
            return reservas.stream().map(ReservasDto::fromEntity)
                    .collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
