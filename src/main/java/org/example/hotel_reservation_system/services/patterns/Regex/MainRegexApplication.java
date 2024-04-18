package org.example.hotel_reservation_system.services.patterns.Regex;

public class MainRegexApplication {
    public static class NameRegex implements IRegexStrategy {
        @Override
        public String applyRegex(String nome) {
            String nameRegex = "[A-Z][a-z].* [A-Z][a-z].*";
            if (nome == null || !nome.matches(nameRegex)) {
                return "Nome inválido";
            }
            return null;
        }
    }

    public static class CpfRegex implements IRegexStrategy {
        @Override
        public String applyRegex(String cpf) {
            String cpfRegex = "^\\d{11}$";
            if (cpf == null || !cpf.matches(cpfRegex)) {
                return "CPF inválido";
            }
            return null;
        }
    }

    public static class CepRegex implements IRegexStrategy {
        @Override
        public String applyRegex(String cep) {
            String cepRegex = "^(?=.*\\d)\\d{1,}$";
            if (cep == null || !cep.matches(cepRegex)) {
                return "CEP inválido";
            }
            return null;
        }
    }

    public static class PaisRegex implements IRegexStrategy {
        @Override
        public String applyRegex(String pais) {
            String paisRegex = "^[a-zA-ZÀ-ÖØ-öø-ÿ ]+$";
            if (pais == null || !pais.matches(paisRegex)) {
                return "País inválido";
            }
            return null;
        }
    }


    public static class RgRegex implements IRegexStrategy{

        @Override
        public String applyRegex(String rg) {
            String rgRegex = "^[0-9]{1,2}.?[0-9]{3}.?[0-9]{3}-?[0-9Xx]$";
            if (rg == null || !rg.matches(rgRegex)) {
                return "Rg inválido";
            }
            return null;
        }
    }
}
