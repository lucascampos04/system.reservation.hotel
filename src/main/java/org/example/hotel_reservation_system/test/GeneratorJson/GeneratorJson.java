package org.example.hotel_reservation_system.test.GeneratorJson;

import org.example.hotel_reservation_system.Enum.Cargo.CargoEmployees;
import org.example.hotel_reservation_system.Enum.Contratos.ContratosEmployees;
import org.example.hotel_reservation_system.Enum.Planos.TipoPlanoEnum;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Random;
import java.util.Scanner;

interface GeneratorStrategy {
    String generate();
    double generateDadsFuncionario();
}

public class GeneratorJson {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ops;

        do {
            System.out.println("[1] - Cliente | [2] - Reserva | [3] - Funcionario | [0] - Sair");
            ops = scanner.nextInt();
            String json = JsonEstrutura(ops);

            if (json != null) {
                System.out.println("Deseja copiar o json ? - [s/n]");
                String input = scanner.next();

                switch (input) {
                    case "s":
                        copyJson(json);
                        break;
                    case "n":
                        System.out.println("Saindo...");
                        break;
                }
            }
        } while (ops != 0);
    }

    private static String JsonEstrutura(int tipoJson) {
        switch (tipoJson) {
            default:
                throw new IllegalArgumentException("Tipo de Json Inválido");
            case 1:
                JsonPessoa("cliente");
                break;
            case 2:
                // reserva
                break;
            case 3:
                JsonPessoa("funcionario");
                break;
            case 0:
                System.out.println("Saindo...");
                break;
        }
        return null;
    }

    private static void copyJson(String json) {
        StringSelection stringSelection = new StringSelection(json);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        System.out.println("Json copiado com sucesso!");
    }

    private static void JsonPessoa(String tipo) {
        NameGenerator nameGenerator = new NameGenerator();
        CpfGenerator cpfGenerator = new CpfGenerator();
        RgGenerator rgGenerator = new RgGenerator();
        GeneratorAdress generatorAdress = new GeneratorAdress();
        GeneratorPlanos generatorPlanos = new GeneratorPlanos();

        CargoGenerator cargoGenerator = new CargoGenerator();
        SalaryGenerator salaryGenerator = new SalaryGenerator();
        GeneratorContrato generatorContrato = new GeneratorContrato();

        String nome = nameGenerator.generate();
        String cpf = cpfGenerator.generate();
        String rg = rgGenerator.generate();
        String endereco = generatorAdress.endereco();
        String numero = generatorAdress.number();
        String cep = generatorAdress.cep();
        String pais = generatorAdress.pais();
        String estado = generatorAdress.estado();
        String contrato = generatorContrato.generate();
        String plano = generatorPlanos.generate();

        String cargo = cargoGenerator.generate();
        double salario = salaryGenerator.generateDadsFuncionario();

        Pessoa pessoa = new Pessoa(nome, cpf, rg, endereco, numero, cep, estado, pais, plano);
        String json;

        json = """
                {
                    "nome": "%s",
                    "email": "camposdlucasoli@gmail.com",
                    "data_nascimento": "29/06/2004",
                    "cpf": "%s",
                    "rg": "%s",
                    "endereco": "%s",
                    "numero": "%s",
                    "cep": "%s",
                    "estado": "%s",
                    "pais": "%s",
                    "plano": "%s",
                }
                """.formatted(pessoa.nome, pessoa.cpf, pessoa.rg, pessoa.endereco, pessoa.numero, pessoa.cep, pessoa.estado, pessoa.pais, pessoa.plano);

        if (tipo.equals("funcionario")) {
            Funcionario funcionario = new Funcionario(nome, cpf, rg, cargo, salario, endereco, numero, cep, estado, pais, contrato, plano);
            json = """
            {
                "nome": "%s",
                "email": "camposdlucasoli@gmail.com",
                "data_nascimento": "29/06/2004",
                "cpf": "%s",
                "rg": "%s",
                "cargo": "%s", 
                "salario": "%.2f", 
                "endereco": "%s",
                "numero": "%s",
                "cep": "%s",
                "estado": "%s",
                "pais": "%s",
                "contrato": "%s",
                "plano": "%s"
            }
            """.formatted(funcionario.nome, funcionario.cpf,
                          funcionario.rg, funcionario.cargo,
                          funcionario.salario, funcionario.endereco,
                          funcionario.numero, funcionario.cep,
                          funcionario.estado, funcionario.pais,
                          funcionario.contrato, funcionario.plano
            );
        }

        System.out.println(json);
    }
}

class Pessoa {
    protected String nome;
    protected String cpf;
    protected String rg;
    protected String endereco;
    protected String numero;
    protected String cep;
    protected String estado;
    protected String pais;
    protected String plano;

    public Pessoa(String nome, String cpf, String rg, String endereco, String numero, String cep, String estado, String pais, String plano) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.endereco = endereco;
        this.numero = numero;
        this.cep = cep;
        this.estado = estado;
        this.pais = pais;
        this.plano = plano;
    }
}

class Funcionario extends Pessoa {
    protected String cargo;
    protected double salario;
    protected String contrato;

    public Funcionario(String nome, String cpf, String rg, String cargo, double salario, String endereco, String numero, String cep, String estado, String pais,String plano, String contrato) {
        super(nome, cpf, rg, endereco, numero, cep, estado, pais, plano);
        this.cargo = cargo;
        this.salario = salario;
        this.contrato = contrato;
    }
}

class GeneratorPlanos implements GeneratorStrategy{
    private final Random random = new Random();

    @Override
    public String generate() {
        TipoPlanoEnum planoEnum = TipoPlanoEnum.values()[random.nextInt(TipoPlanoEnum.values().length)];
        return planoEnum.toString();
    }

    @Override
    public double generateDadsFuncionario() {
        return 0;
    }
}

class GeneratorContrato implements GeneratorStrategy {
    private final Random random = new Random();

    @Override
    public String generate() {
        ContratosEmployees contratosEmployees = ContratosEmployees.values()[random.nextInt(ContratosEmployees.values().length)];
        return contratosEmployees.toString();
    }


    @Override
    public double generateDadsFuncionario() {
        return 0;
    }
}

class GeneratorAdress implements GeneratorStrategy {
    private final String[] alfabeto = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private final String[] streets = {"Rua A", "Avenida B", "Praça C", "Travessa D", "Alameda E"};
    private final String[] cities = {"City", "City", "City", "City", "City"};
    private final String[] states = {"State", "State", "State", "State", "State"};
    private final String[] countries = {"Brasil", "Estados Unidos", "Russia", "Canada", "Espanha"};

    private final Random random = new Random();

    @Override
    public String generate() {
        return "";
    }

    @Override
    public double generateDadsFuncionario() {
        return 0;
    }

    public String number(){
        int numLetras = random.nextInt(3);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numLetras; i++) {
            sb.append(alfabeto[random.nextInt(alfabeto.length)]);
        }

        int numDigitos = 3 - numLetras;
        for (int i = 0; i < numDigitos; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public String endereco(){
        return streets[random.nextInt(streets.length)] + ", " + cities[random.nextInt(cities.length)];
    }

    public String cep(){
        StringBuilder cep = new StringBuilder();

        for (int i = 0; i < 8; i++){
            cep.append(random.nextInt(10));
        }

        return cep.toString();
    }

    public String estado(){
        return states[random.nextInt(states.length)];
    }

    public String pais(){
        return countries[random.nextInt(countries.length)];
    }
}

class NameGenerator implements GeneratorStrategy {
    private final String[] prefixes = {"John", "David", "Michael", "Sarah", "Emily", "Emma", "James", "William"};
    private final String[] suffixes = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};
    private final Random random = new Random();

    @Override
    public String generate() {
        String prefix1 = prefixes[random.nextInt(prefixes.length)];
        String prefix2;

        do {
            prefix2 = prefixes[random.nextInt(prefixes.length)];
        } while (prefix2.equals(prefix1));

        String suffix = suffixes[random.nextInt(suffixes.length)];

        return prefix1 + " " + prefix2 + " " + suffix;
    }

    @Override
    public double generateDadsFuncionario() {
        return 0;
    }
}

class RgGenerator implements GeneratorStrategy {
    private final Random random = new Random();

    @Override
    public String generate() {
        StringBuilder rg = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            rg.append(random.nextInt(10));
        }

        return rg.toString();
    }

    @Override
    public double generateDadsFuncionario() {
        return 0;
    }
}

class CpfGenerator implements GeneratorStrategy {
    private final Random random = new Random();

    @Override
    public String generate() {
        StringBuilder cpf = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            cpf.append(random.nextInt(10));
        }
        return cpf.toString();
    }

    @Override
    public double generateDadsFuncionario() {
        return 0;
    }
}

class CargoGenerator implements GeneratorStrategy {
    private final Random random = new Random();
    private final CargoEmployees cargoEmployees = CargoEmployees.values()[random.nextInt(CargoEmployees.values().length)];

    @Override
    public String generate() {
        return cargoEmployees.toString();
    }

    @Override
    public double generateDadsFuncionario() {
        return 0;
    }
}

class SalaryGenerator implements GeneratorStrategy {
    private final Random random = new Random();
    private final CargoGenerator cargoGenerator = new CargoGenerator();
    private double min;
    private double max;

    @Override
    public String generate() {
        return "";
    }

    @Override
    public double generateDadsFuncionario() {
        double salary = 0;
        String cargo = cargoGenerator.generate();

        switch (cargo){
            case "GERENTE":
                min = 3000;
                max = 10000;
                salary = min + random.nextDouble() * (max - min);
                break;
            case "RECEPCIONISTA":
            case "CAMAREIRA":
            case "GARCOM":
            case "MANOBRISTA":
            case "LIMPEZA":
                min = 2000;
                max = 5000;
                salary = min + random.nextDouble() * (max - min);
                break;
            case "SEGURANCA":
            case "COZINHEIRO":
                min = 2500;
                max = 7000;
                salary = min + random.nextDouble() * (max - min);
                break;
            default:
                throw new IllegalArgumentException("Cargo inválido");
        }
        return salary;
    }
}
