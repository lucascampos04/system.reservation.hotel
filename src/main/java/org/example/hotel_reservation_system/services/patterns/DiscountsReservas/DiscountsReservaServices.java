package org.example.hotel_reservation_system.services.patterns.DiscountsReservas;

import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;
import org.springframework.stereotype.Service;

@Service
public class DiscountsReservaServices {
    private final DiscountStrategy basicDiscountStrategy;
    private final DiscountStrategy familyDiscountStrategy;
    private final DiscountStrategy luxuryDiscountStrategy;

    public DiscountsReservaServices(
             DiscountStrategy basicDiscountStrategy,
             DiscountStrategy familyDiscountStrategy,
             DiscountStrategy luxuryDiscountStrategy) {
        this.basicDiscountStrategy = basicDiscountStrategy;
        this.familyDiscountStrategy = familyDiscountStrategy;
        this.luxuryDiscountStrategy = luxuryDiscountStrategy;
    }

    public Double applyDiscount(Double valor, TipoPlanoEnum plano) {
        switch (plano) {
            case BASICO:
            case PADRAO:
                return basicDiscountStrategy.aplyDiscount(valor);
            case FAMILIA:
            case LONGA_ESTADIA:
                return familyDiscountStrategy.aplyDiscount(valor);
            case LUXO:
            case EXECUTIVO:
            case VIP:
                return luxuryDiscountStrategy.aplyDiscount(valor);
            default:
                return valor;
        }

    }
}
