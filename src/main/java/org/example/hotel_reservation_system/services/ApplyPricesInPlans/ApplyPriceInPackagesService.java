package org.example.hotel_reservation_system.services.ApplyPricesInPlans;

import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;
import org.springframework.stereotype.Service;

@Service
public class ApplyPriceInPackagesService {

    public static Double applyPriceInPlans(Double valor, PacoteEnum pacote) {
        if (pacote.equals(PacoteEnum.DIARIA)){
            valor = 300.0;
            return valor;
        } else if (pacote.equals(PacoteEnum.MENSAL)){
            valor = 3000.0;
            return valor;
        } else if (pacote.equals(PacoteEnum.ANUAL)){
            valor = 100000.0;
            return valor;
        } else if (pacote.equals(PacoteEnum.SEMANAL)) {
            valor = 800.0;
            return valor;
        } else if (pacote.equals(PacoteEnum.BIMESTRAL)) {
            valor = 10000.0;
            return valor;
        } else if (pacote.equals(PacoteEnum.TRIMESTRAL)) {
            valor = 15000.0;
            return valor;
        }

        return valor;
    }
}
