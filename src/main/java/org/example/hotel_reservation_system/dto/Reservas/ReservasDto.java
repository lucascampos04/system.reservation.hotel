package org.example.hotel_reservation_system.dto.Reservas;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.example.hotel_reservation_system.Enum.Pacote.PacoteEnum;
import org.example.hotel_reservation_system.Enum.Status.StatusPagamentoEnum;
import org.example.hotel_reservation_system.Enum.formaPagamento.FormaDePagemntoEnum;
import org.example.hotel_reservation_system.model.Clientes.ClientesEntity;
import org.example.hotel_reservation_system.model.Reservas.ReservasEntity;
import org.example.hotel_reservation_system.repository.Clientes.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.Reservas.ReservasEntity}
 */
@Getter
@Setter
public class ReservasDto implements Serializable {

    Long id;
    PacoteEnum pacote;
    FormaDePagemntoEnum formaPagamento;
    Double valor;
    LocalDateTime dataPagamento;
    StatusPagamentoEnum statusPagamento;
    int quantidadePessoas;
    Long clienteId;
    String nome;

    public static ReservasDto fromEntity(ReservasEntity entity, ClientesRepository clientesRepository) {

        ClientesEntity clientes = clientesRepository.findById(entity.getClienteId()).orElse(null);

        String nomeCliente = clientes != null ? clientes.getNome() : null;

        return new ReservasDto(
                entity.getId(),
                entity.getPacote(),
                entity.getFormaPagamento(),
                entity.getValor(),
                entity.getDataPagamento(),
                entity.getStatusPagamento(),
                entity.getQuantidadePessoas(),
                entity.getClienteId(),
                nomeCliente
        );
    }

    public ReservasDto(Long id, PacoteEnum pacote,
                       FormaDePagemntoEnum formaPagamento,
                       Double valor, LocalDateTime dataPagamento,
                       StatusPagamentoEnum statusPagamento, int quantidadePessoas, Long clienteId, String nome) {
        this.id = id;
        this.pacote = pacote;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.statusPagamento = statusPagamento;
        this.quantidadePessoas = quantidadePessoas;
        this.clienteId = clienteId;
        this.nome = nome;
    }
}