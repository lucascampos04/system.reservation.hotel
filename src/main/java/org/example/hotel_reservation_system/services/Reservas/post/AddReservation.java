package org.example.hotel_reservation_system.services.Reservas.post;

import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.Enum.Status.StatusEnum;
import org.example.hotel_reservation_system.dto.Reservas.ReservasDto;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.example.hotel_reservation_system.repository.Reservas.ReservasRepository;
import org.example.hotel_reservation_system.services.ApplyPricesInPlans.ApplyPriceInPackagesService;
import org.example.hotel_reservation_system.services.patterns.DiscountsReservas.DiscountsReservaServices;
import org.example.hotel_reservation_system.services.EmailServices.PaymentsSuccess.NotificationPaymentSuccessServices;
import org.example.hotel_reservation_system.services.EmailServices.Reservas.NotificationReservaCongratulations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AddReservation {
    private final ReservasRepository reservasRepository;
    private final ClientesRepository clientesRepository;
    private final DiscountsReservaServices discountsReservaServices;
    private final NotificationReservaCongratulations notificationReservaCongratulations;
    private final NotificationPaymentSuccessServices notificationPaymentSuccessServices;
    public AddReservation(ReservasRepository reservasRepository, ClientesRepository clientesRepository, DiscountsReservaServices discountsReservaServices, NotificationReservaCongratulations notificationReservaCongratulations, NotificationPaymentSuccessServices notificationPaymentSuccessServices) {
        this.reservasRepository = reservasRepository;
        this.clientesRepository = clientesRepository;
        this.discountsReservaServices = discountsReservaServices;
        this.notificationReservaCongratulations = notificationReservaCongratulations;
        this.notificationPaymentSuccessServices = notificationPaymentSuccessServices;
    }

    public ResponseEntity<String> addReservation(ReservasDto reservasDto) {
        try {
            ReservasEntity reservasEntity = getDads(reservasDto);
            reservasRepository.save(reservasEntity);
            
            notificationReservaCongratulations.SendEmailReservaCongratulations(reservasDto.getNomeCliente(), reservasEntity.getValor(), reservasDto.getEmailCliente());
            notificationPaymentSuccessServices.SendEmailPaymentCongratulations(reservasDto.getNomeCliente(), reservasEntity.getValor(), reservasDto.getEmailCliente(), reservasDto.getFormaPagamento());

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
        reservasEntity.setFormaPagamento(reservasDto.getFormaPagamento());

        if (reservasDto.getIdCliente() != null){
            reservasEntity.setStatus(StatusEnum.ATIVO);
        } else {
            reservasEntity.setStatus(StatusEnum.INATIVO);
        }


        if (reservasDto.getIdCliente() != null){
            Optional<ClientesEntity> clientesOptional = clientesRepository.findById(reservasDto.getIdCliente());
            clientesOptional.ifPresent(clientes -> {

                Double applyPriceInPackages = ApplyPriceInPackagesService.applyPriceInPlans(reservasDto.getValor(), reservasDto.getPackageName());
                Double valueDiscount = discountsReservaServices.applyDiscount(applyPriceInPackages, clientes.getPlano().getPlano());

                reservasEntity.setValorSemDesconto(applyPriceInPackages);

                reservasEntity.setValor(valueDiscount);

                reservasDto.setNomeCliente(clientes.getNome());
                reservasDto.setEmailCliente(clientes.getEmail());
                reservasDto.setPlanoCliente(clientes.getPlano().getPlano());
                reservasDto.setRoleCliente(clientes.getRole());
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