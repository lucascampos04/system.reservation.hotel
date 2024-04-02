package org.example.hotel_reservation_system.services.Reservas.post;

import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.example.hotel_reservation_system.repository.Reservas.ReservasRepository;
import org.example.hotel_reservation_system.services.DiscountsReservas.DiscountsReservaServices;
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

            String validarCampos = validarCamposPatterns(reservasDto);
            if (validarCampos != null){
                return ResponseEntity.badRequest().body("Error: " + validarCampos);
            }

            ReservasEntity reservasEntity = getDads(reservasDto);
            reservasRepository.save(reservasEntity);
            return ResponseEntity.ok("Reserva adicionada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Erro ao adicionar reserva");
        }
    }

    private String validarCamposPatterns(ReservasDto reservasDto) {
        if (!isValidPackageName(reservasDto.getPackageName())){
            return "Pacote inválido";
        }

        if (!containsWhitespace(String.valueOf(reservasDto.getId()))){
            return "Id invalido";
        }

        return null;
    }

    private ReservasEntity getDads(ReservasDto reservasDto) {
        ReservasEntity reservasEntity = new ReservasEntity();

        reservasEntity.setId(genertatorId());
        reservasEntity.setPackageName(reservasDto.getPackageName());
        reservasEntity.setData_checkin(LocalDateTime.now());


        if (reservasDto.getIdCliente() != null){
            Optional<ClientesEntity> clientesOptional = clientesRepository.findById(reservasDto.getIdCliente());
            clientesOptional.ifPresent(clientes -> {

                Double valueDIscount = DiscountsReservaServices.appluyDiscount(reservasDto.getValor(), clientes.getPlano().getPlano());
                reservasEntity.setValor(valueDIscount);

                reservasDto.setNome(clientes.getNome());
                reservasDto.setEmailCliente(clientes.getEmail());
                reservasDto.setPlanoCliente(clientes.getPlano().getPlano());
                reservasEntity.setCliente(clientes);
            });
        }

        return reservasEntity;
    }

    private boolean isValidPackageName(PacoteEnum packageNameEnum) {
        String[] packageNames = {
                String.valueOf(PacoteEnum.DIARIA),
                String.valueOf(PacoteEnum.MENSAL),
                String.valueOf(PacoteEnum.ANUAL),
                String.valueOf(PacoteEnum.BIMESTRAL),
                String.valueOf(PacoteEnum.TRIMESTRAL),
                String.valueOf(PacoteEnum.SEMESTRAL),
                String.valueOf(PacoteEnum.SEMANAL),
        };

        for (int i = 0; i < packageNames.length; i++){
            if (packageNames.equals(String.valueOf(packageNameEnum))){
                return true;
            }
        }
        return false;
    }

    private Long genertatorId() {
        long id = 0;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        do {
            id = 100000000 + random.nextLong(999999999);
        } while (reservasRepository.existsById(id));
        return id;
    }

    private boolean containsWhitespace(String value) {
        return value != null && value.contains(" ");
    }
}
