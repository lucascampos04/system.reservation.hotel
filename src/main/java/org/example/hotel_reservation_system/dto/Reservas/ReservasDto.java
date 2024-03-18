package org.example.hotel_reservation_system.dto.Reservas;

import lombok.Value;
import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.Enum.Status.StatusPagamentoEnum;
import org.example.hotel_reservation_system.Enum.formaPagamento.FormaDePagemntoEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.Reservas.ReservasEntity}
 */
@Value
public class ReservasDto implements Serializable {
    Long id;
    PacoteEnum pacote;
    FormaDePagemntoEnum formaPagamento;
    Double valor;
    LocalDateTime dataPagamento;
    StatusPagamentoEnum statusPagamento;
    int quantidadePessoas;


    public ReservasDto(Long id, PacoteEnum pacote,
                       FormaDePagemntoEnum formaPagamento,
                       Double valor, LocalDateTime dataPagamento,
                       StatusPagamentoEnum statusPagamento, int quantidadePessoas) {
        this.id = id;
        this.pacote = pacote;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.statusPagamento = statusPagamento;
        this.quantidadePessoas = quantidadePessoas;
    }
}