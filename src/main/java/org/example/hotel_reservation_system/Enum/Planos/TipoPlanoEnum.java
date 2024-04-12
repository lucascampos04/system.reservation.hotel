package org.example.hotel_reservation_system.Enum.Planos;

import lombok.Getter;

@Getter
public enum TipoPlanoEnum {
    SEM_PLANO("Sem plano"),
    BASICO("Plano Básico"),
    PADRAO("Plano Padrão"),
    EXECUTIVO("Plano Executivo"),
    LUXO("Plano de Luxo"),
    FAMILIA("Pacote Família"),
    LONGA_ESTADIA("Plano de Longa Estadia"),
    COBERTURA("Plano de Cobertura"),
    NEGOCIO("Plano de negocio"),
    VIP("Pacote VIP");

    private final String descricao;

    TipoPlanoEnum(String descricao) {
        this.descricao = descricao;
    }

}
