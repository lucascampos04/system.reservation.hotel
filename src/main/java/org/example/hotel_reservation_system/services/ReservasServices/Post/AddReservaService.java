package org.example.hotel_reservation_system.services.ReservasServices.Post;

import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.example.hotel_reservation_system.repository.Reservas.ReservasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AddReservaService {

    private final ReservasRepository reservasRepository;

    public AddReservaService(ReservasRepository reservasRepository) {
        this.reservasRepository = reservasRepository;
    }

    public ResponseEntity<String> addReservas(ReservasDto reservasDto) {
        try{
            ClientesEntity cliente = new ClientesEntity();
            ReservasEntity entity = getDads(reservasDto);
            reservasRepository.save(entity);
            return ResponseEntity.ok().body(entity.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private ReservasEntity getDads(ReservasDto reservasDto) {
        ReservasEntity entity = new ReservasEntity();
        ClientesEntity clientes = new ClientesEntity();
        entity.setId(reservasDto.getId());
        entity.setPacote(reservasDto.getPacote());
        entity.setFormaPagamento(reservasDto.getFormaPagamento());
        entity.setValor(reservasDto.getValor());
        entity.setDataPagamento(LocalDateTime.now());
        entity.setClienteId(entity.getClienteId());
        return entity;
    }
}
