package org.example.hotel_reservation_system.services.ReservasServices.Get;

import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.example.hotel_reservation_system.repository.Reservas.ReservasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllReservasServices {
    @Autowired
    private ReservasRepository reservasRepository;
    @Autowired
    private ClientesRepository clientesRepository;

    public List<ReservasDto> listAllReservas(){
        try{
            List<ReservasEntity> reservas = reservasRepository.findAll();
            return reservas.stream()
                    .map(reserva -> ReservasDto.fromEntity(reserva, clientesRepository))
                    .collect(Collectors.toList());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
