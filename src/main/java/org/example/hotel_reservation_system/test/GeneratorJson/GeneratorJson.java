package org.example.hotel_reservation_system.test.GeneratorJson;

import java.util.Random;

public class GeneratorJson {
    public static void main(String[] args) {
        System.out.println("{");
        System.out.println("  \"nome\": \"" + generateName() + "\",");
        System.out.println("  \"email\": \"camposdlucasoli@gmail.com\",");
        System.out.println("  \"cpf\": \"12345678900\",");
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
}
