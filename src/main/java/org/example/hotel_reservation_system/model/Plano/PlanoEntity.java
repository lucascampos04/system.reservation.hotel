package org.example.hotel_reservation_system.model.Plano;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;

@Getter
@Setter
@Entity
public class PlanoEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "plano")
    private TipoPlanoEnum plano;

    @Column(name = "valor")
    private Double valor;

    @OneToOne(mappedBy = "plano")
    private ClientesEntity cliente;
    public PlanoEntity(){

    }

}