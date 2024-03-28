package org.example.hotel_reservation_system.model.Reservas;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.Enum.Status.StatusPagamentoEnum;
import org.example.hotel_reservation_system.Enum.formaPagamento.FormaDePagemntoEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "reservas_entity")
public class ReservasEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "pacote")
    @Enumerated(EnumType.STRING)
    private PacoteEnum pacote;

    @Column(name = "forma_pagamento")
    @Enumerated(EnumType.STRING)
    private FormaDePagemntoEnum formaPagamento;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data_pagamento")
    private LocalDateTime dataPagamento;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum statusPagamento;

    @Column(name = "quantidade_pessoas")
    private int quantidadePessoas;

    @Column(name = "cliente_id")
    private Long clienteId;

    public ReservasEntity() {

    }
}