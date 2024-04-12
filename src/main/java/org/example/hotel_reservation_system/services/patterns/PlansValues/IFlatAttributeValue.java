package org.example.hotel_reservation_system.services.patterns.PlansValues;

import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;

public interface IFlatAttributeValue {
    double applyValues(TipoPlanoEnum planoEnum, double value);
}
