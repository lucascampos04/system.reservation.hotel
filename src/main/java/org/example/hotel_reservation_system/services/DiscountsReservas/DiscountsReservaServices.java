package org.example.hotel_reservation_system.services.DiscountsReservas;

import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;
import org.springframework.stereotype.Service;
@Service
public class DiscountsReservaServices {

    public static Double appluyDiscount(Double valor, TipoPlanoEnum plano) {
        if (plano.equals(TipoPlanoEnum.BASICO) || plano.equals(TipoPlanoEnum.PADRAO)){
            return valor - (valor * 0.05);
        } else if (plano.equals(TipoPlanoEnum.FAMILIA) || plano.equals(TipoPlanoEnum.LONGA_ESTADIA)){
            return valor - (valor * 0.1);
        } else if (plano.equals(TipoPlanoEnum.LUXO) || plano.equals(TipoPlanoEnum.EXECUTIVO) || plano.equals(TipoPlanoEnum.VIP)){
            return valor - (valor * 0.15);
        } else {
            return valor;
        }
    }
}

