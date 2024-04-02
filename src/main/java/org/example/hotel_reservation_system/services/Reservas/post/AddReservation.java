package org.example.hotel_reservation_system.services.Reservas.post;

import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.example.hotel_reservation_system.repository.Reservas.ReservasRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AddReservation {
    private final ReservasRepository reservasRepository;
    private final ClientesRepository clientesRepository;
    public AddReservation(ReservasRepository reservasRepository, ClientesRepository clientesRepository) {
        this.reservasRepository = reservasRepository;
        this.clientesRepository = clientesRepository;
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
        reservasEntity.setId(genertatorId());
        reservasEntity.setPackageName(reservasDto.getPackageName());
        reservasEntity.setData_checkin(LocalDateTime.now());
        reservasEntity.setValor(reservasDto.getValor());

        if (reservasDto.getIdCliente() != null){
            Optional<ClientesEntity> clientesOptional = clientesRepository.findById(reservasDto.getIdCliente());
            clientesOptional.ifPresent(clientes -> {
                reservasDto.setNome(clientes.getNome());
                reservasDto.setEmailCliente(clientes.getEmail());
                reservasEntity.setCliente(clientes);
            });
        }

        return reservasEntity;
    }

    private Long genertatorId() {
        long id = 0;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        do {
            id = 100000000 + random.nextLong(999999999);
        } while (reservasRepository.existsById(id));
        return id;
    }
}
