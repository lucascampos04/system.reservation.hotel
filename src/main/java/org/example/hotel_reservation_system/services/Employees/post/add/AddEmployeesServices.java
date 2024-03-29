package org.example.hotel_reservation_system.services.Employees.post.add;

import org.example.hotel_reservation_system.Enum.Cargo.CargoEmployees;
import org.example.hotel_reservation_system.dto.Employees.EmployeesDto;
import org.example.hotel_reservation_system.model.Employees.EmployeesEntity;
import org.example.hotel_reservation_system.repository.Employees.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddEmployeesServices {
    @Autowired
    private EmployeesRepository employeesRepository;

    public ResponseEntity<String> addEmployees(EmployeesDto employeesDto){
        try {
            String validarCampos = ValidateEmployeesPattern(employeesDto);

            if (validarCampos != null){
                return ResponseEntity.badRequest().body("Error : " + validarCampos);
            }

            EmployeesEntity entity = getDads(employeesDto);
            employeesRepository.save(entity);

            return ResponseEntity.ok().body("Funcionario adicionado com sucesso");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    private String ValidateEmployeesPattern(EmployeesDto dto){
        if( containsWhitespace(dto.getNome()) || 
            containsWhitespace(dto.getEmail()) || 
            containsWhitespace(dto.getCpf()) || 
            containsWhitespace(dto.getRg())){
            return "Preencha todos os campos";
        }
        if(!isValidCpf(dto.getCpf())){
            return "CPF inválido";
        }
        if(!isValidRg(dto.getRg())){
            return "RG inválido";
        }
        if(!isValidName(dto.getNome())){
            return "Nome inválido";
        }
        if(!isValidStatus(String.valueOf(dto.getStatus()))){
            return "Status inválido";
        }
        if(!isValidCep(dto.getCep())){
            return "CEP inválido";
        }
        if(!isValidPais(dto.getPais())){
            return "País inválido";
        }
        if(!isValidSalario(String.valueOf((dto.getSalario())))){
            return "Salário inválido";
        }
        if (!validarSalary(dto)){
            return "Salário inválido";
        }
        return null;
    }

    private boolean containsWhitespace(String value) {
        return value != null && value.contains(" ");
    }
    private boolean isValidCpf(String cpf){
        String cpfRegex = "^\\d{11}$";
        return cpf != null && cpf.matches(cpfRegex);
    }
    private boolean isValidRg(String rg){
        String rgRegex = "^[0-9]{1,2}.?[0-9]{3}.?[0-9]{3}-?[0-9Xx]$";
        return rg != null && rg.matches(rgRegex);
    }
    private boolean isValidName(String name){
        String nameRegex = "[A-Z][a-z].* [A-Z][a-z].*";
        return name != null && name.matches(nameRegex);
    }
    private boolean isValidStatus(String status){
        return status.equals("ATIVO") || status.equals("DESATIVADO");
    }
    private boolean isValidCep(String cep){
        String cepfRegex = "^(?=.*\\d)\\d{1,}$";
        return cep != null && cep.matches(cepfRegex);
    }
    private boolean isValidPais(String pais){
        String paisRegex = "^(?=.*[a-zA-Z])[a-zA-Z]{1,}$";
        return pais != null && pais.matches(paisRegex);
    }

    private boolean isValidSalario(String salario){
        String salarioRegex =" ^[0-9]+(\\.[0-9]+)?$\n";
        return salario != null && salario.matches(salarioRegex);
    }

    private boolean validarSalary(EmployeesDto employeesDto) {
        CargoEmployees cargo = employeesDto.getCargo();
        double salario = employeesDto.getSalario();

        switch (cargo) {
            case GERENTE:
                if (salario <= 10000) {
                    return false;
                }
                break;
            case RECEPCIONISTA:
                if (salario >= 3500) {
                    return false;
                }
                break;
            case CAMAREIRA, MANOBRISTA:
                if (salario >= 3000) {
                    return false;
                }
                break;
            case GARCOM:
                if (salario >= 2500) {
                    return false;
                }
                break;
            case COZINHEIRO:
                if (salario >= 4000) {
                    return false;
                }
                break;
            case SEGURANCA:
                if (salario >= 5500) {
                    return false;
                }
                break;
            case LIMPEZA, OUTROS:
                if (salario >= 2000) {
                    return false;
                }
                break;
        }
        return true;
    }

    private EmployeesEntity getDads(EmployeesDto dto){
        EmployeesEntity employeesEntity = new EmployeesEntity();
        employeesEntity.setId(dto.getId());
        employeesEntity.setNome(dto.getNome());
        employeesEntity.setEmail(dto.getEmail());
        employeesEntity.setCpf(dto.getCpf());
        employeesEntity.setRg(dto.getRg());
        employeesEntity.setDataNascimeo(dto.getDataNascimeo());
        employeesEntity.setStatus(dto.getStatus());
        employeesEntity.setCargo(dto.getCargo());
        employeesEntity.setData_registro(dto.getData_registro());
        employeesEntity.setSalario(dto.getSalario());
        employeesEntity.setCep(dto.getCep());
        employeesEntity.setNumero(dto.getNumero());
        employeesEntity.setEstado(dto.getEstado());
        employeesEntity.setPais(dto.getPais());
        return employeesEntity;
    }
}
