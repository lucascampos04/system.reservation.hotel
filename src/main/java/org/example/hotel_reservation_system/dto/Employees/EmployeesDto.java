package org.example.hotel_reservation_system.dto.Employees;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.example.hotel_reservation_system.Enum.Cargo.CargoEmployees;
import org.example.hotel_reservation_system.Enum.Contratos.ContratosEmployees;
import org.example.hotel_reservation_system.Enum.Status.StatusEmployees;
import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link org.example.hotel_reservation_system.model.Employees.EmployeesEntity}
 */
@Getter
@Setter
public class EmployeesDto implements Serializable {
    Long Id;
    String nome;
    String email;
    String cpf;
    String rg;
    String endereco;
    String cep;
    String numero;
    String estado;
    String pais;
    String dataNascimeo;
    StatusEmployees status;
    CargoEmployees cargo;
    ContratosEmployees contratos;
    LocalDateTime data_registro;
    Double salario;

    public static EmployeesDto fromEntity(EmployeesEntity entity){
        return new EmployeesDto(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getRg(),
                entity.getEndereco(),
                entity.getCep(),
                entity.getNumero(),
                entity.getEstado(),
                entity.getPais(),
                entity.getDataNascimeo(),
                entity.getStatus(),
                entity.getCargo(),
                entity.getContratos(),
                entity.getData_registro(),
                entity.getSalario()
        );
    }

    public EmployeesDto(Long id, String nome, String email, String cpf, String rg, String endereco, String cep, String numero, String estado,
                        String pais, String dataNascimeo, StatusEmployees status, CargoEmployees cargo, ContratosEmployees contratos, LocalDateTime data_registro, Double salario) {
        Id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.cep = cep;
        this.numero = numero;
        this.estado = estado;
        this.pais = pais;
        this.dataNascimeo = dataNascimeo;
        this.status = status;
        this.cargo = cargo;
        this.contratos = contratos;
        this.data_registro = data_registro;
        this.salario = salario;
    }
}