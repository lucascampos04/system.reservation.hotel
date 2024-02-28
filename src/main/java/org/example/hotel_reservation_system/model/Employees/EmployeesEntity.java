package org.example.hotel_reservation_system.model.Employees;

import java.time.LocalDateTime;

import org.example.hotel_reservation_system.Enum.Cargo.CargoEmployees;
import org.example.hotel_reservation_system.Enum.Contratos.ContratosEmployees;
import org.example.hotel_reservation_system.Enum.Status.StatusEmployees;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "employees")
@Getter
@Setter
public class EmployeesEntity {
    public static final String ResponseEntity = null;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "rg", unique = true)
    private String rg;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "cep")
    private String cep;

    @Column(name = "numero")
    private String numero;

    @Column(name = "estado")
    private String estado;

    @Column(name = "pais")
    private String pais;

    @Column(name = "data_nascimento")
    private String dataNascimeo; 

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'DESATIVADO'")
    private StatusEmployees status;

    @Column(name = "cargo")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'DESATIVADO'")
    private CargoEmployees cargo;
    
    @Column(name = "contrato")
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'Um Ano'")
    private ContratosEmployees contratos;

    @Column(name = "data_registro")
    private LocalDateTime data_registro;

    @Column(name = "salario")
    private Double salario;

    public EmployeesEntity(Long id, 
        String nome, 
        String email, 
        String cpf, 
        String rg, 
        String dataNascimeo, 
        StatusEmployees status, 
        CargoEmployees cargo, 
        LocalDateTime data_registro, 
        Double salario,
        String cep,
        String numero,
        String estado,
        String pais,
        String endereco) {
            this.Id = id;
            this.nome = nome;
            this.email = email;
            this.cpf = cpf;
            this.rg = rg;
            this.dataNascimeo = dataNascimeo;
            this.status = status;
            this.cargo = cargo;
            this.data_registro = data_registro;
            this.salario = salario;
            this.cep = cep;
            this.numero = numero;
            this.estado = estado;
            this.pais = pais;
            this.endereco = endereco;
    }

    public EmployeesEntity() {
        
    }


    
}
