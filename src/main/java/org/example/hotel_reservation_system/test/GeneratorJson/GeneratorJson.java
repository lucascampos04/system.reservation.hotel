package org.example.hotel_reservation_system.test.GeneratorJson;

import java.util.Random;

public class GeneratorJson{
    public static void main(String[] args) {
        System.out.println("{");
        System.out.println("  \"nome\": \"" + generateName() + "\",");
        System.out.println("  \"email\": \"camposdlucasoli@gmail.com\",");
        System.out.println("  \"cpf\": \"" + generateCpf() + "\",");
        System.out.println("  \"rg\": \"12.345.678-9\",");
        System.out.println("  \"endereco\": \"Endereço Aleatório\",");
        System.out.println("  \"cep\": \"12345678\",");
        System.out.println("  \"numero\": 123,");
        System.out.println("  \"estado\": \"Estado Aleatório\",");
        System.out.println("  \"pais\": \"País Aleatório\",");
        System.out.println("  \"data_nascimento\": \"1990-01-01T00:00:00\"");
        System.out.println("}");
    }

    private static String generateName() {
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        Random random = new Random();
        StringBuilder generator = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                int index = random.nextInt(alphabet.length);
                generator.append(alphabet[index]);
            }

            if (i < 2){
                generator.append(" ");
            }
        }
        return generator.toString();
    }

    private static String generateCpf() {
        Random random = new Random();
        int[] cpf = new int[9];

        // Gerar os 9 primeiros dígitos do CPF
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10); // Limite o intervalo de 0 a 9
        }

        // Calcular os dígitos verificadores do CPF
        int[] digitosVerificadores = new int[2];
        for (int i = 0; i < 2; i++) {
            int soma = 0;
            int multiplicador = 2 + i; // O primeiro multiplicador é 2 e o segundo é 3
            for (int j = 0; j < cpf.length + i; j++) {
                soma += cpf[j % cpf.length] * multiplicador;
                multiplicador--;
                if (multiplicador < 2) {
                    multiplicador = 9; // Reinicia o multiplicador
                }
            }
            digitosVerificadores[i] = 11 - (soma % 11);
            if (digitosVerificadores[i] >= 10) {
                digitosVerificadores[i] = 0;
            }
        }

        // Formatar o CPF como string
        StringBuilder cpfString = new StringBuilder();
        for (int i = 0; i < cpf.length; i++) {
            cpfString.append(cpf[i]);
            if (i == 2 || i == 5) {
                cpfString.append(".");
            } else if (i == 8) {
                cpfString.append("-");
            }
        }
        cpfString.append(digitosVerificadores[0]);
        cpfString.append(digitosVerificadores[1]);

        return cpfString.toString();
    }
}
