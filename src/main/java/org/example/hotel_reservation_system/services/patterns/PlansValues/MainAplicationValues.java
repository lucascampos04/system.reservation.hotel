package org.example.hotel_reservation_system.services.patterns.PlansValues;

import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;

import java.util.Map;

public class MainAplicationValues {

    private static final Map<TipoPlanoEnum, Double> VALORES_DO_PLANO = Map.of(
            TipoPlanoEnum.PADRAO, 1000.0,
            TipoPlanoEnum.BASICO, 500.0,
            TipoPlanoEnum.FAMILIA, 1500.0,
            TipoPlanoEnum.LUXO, 5000.0,
            TipoPlanoEnum.EXECUTIVO, 8000.0,
            TipoPlanoEnum.LONGA_ESTADIA, 6000.0,
            TipoPlanoEnum.COBERTURA, 4000.0,
            TipoPlanoEnum.VIP, 8300.0,
            TipoPlanoEnum.NEGOCIO, 20300.0
    );
    public static class FlatValue implements IFlatAttributeValue {
        @Override
        public double applyValues(TipoPlanoEnum planoEnum, double value) {
            return VALORES_DO_PLANO.getOrDefault(planoEnum, 0.0);
        }
    }
}
