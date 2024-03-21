package org.example.hotel_reservation_system.test.GeneratorJson;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GeneratorJson {
    public static void main(String[] args) {
        System.out.println("{");
        System.out.println("  \"nome\": \"" + generateName() + "\",");
        System.out.println("  \"email\": \"camposdlucasoli@gmail.com\",");
        System.out.println("  \"cpf\": \"" + generateCpf() + "\",");
        System.out.println("  \"rg\": \"" + generateRg() + "\",");
        System.out.println("  \"endereco\": \"" + generateAddress() + "\",");
        System.out.println("  \"cep\": \"" + generateCep() + "\",");
        System.out.println("  \"numero\": " + generateNumber() + ",");
        System.out.println("  \"estado\": \"" + generateState() + "\",");
        System.out.println("  \"pais\": \"" + generateCountry() + "\",");
        System.out.println("  \"data_nascimento\": \"29/06/2004\"");
        System.out.println("}");

        try {
            Robot robot = new Robot();
            robot.delay(2000); // Espera 2 segundos antes de pressionar a tecla
            robot.keyPress(KeyEvent.VK_C); // Pressiona a tecla "c"
            robot.keyRelease(KeyEvent.VK_C); // Libera a tecla "c"
            copyToClipboard(getPrintedContent()); // Copia o conteúdo do print para a área de transferência
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static String getPrintedContent() {
        StringBuilder printedContent = new StringBuilder();
        printedContent.append("{\n");
        printedContent.append("  \"nome\": \"" + generateName() + "\",\n");
        printedContent.append("  \"email\": \"camposdlucasoli@gmail.com\",\n");
        printedContent.append("  \"cpf\": \"" + generateCpf() + "\",\n");
        printedContent.append("  \"rg\": \"" + generateRg() + "\",\n");
        printedContent.append("  \"endereco\": \"" + generateAddress() + "\",\n");
        printedContent.append("  \"cep\": \"" + generateCep() + "\",\n");
        printedContent.append("  \"numero\": " + generateNumber() + ",\n");
        printedContent.append("  \"estado\": \"" + generateState() + "\",\n");
        printedContent.append("  \"pais\": \"" + generateCountry() + "\",\n");
        printedContent.append("  \"data_nascimento\": \"29/06/2004\"\n");
        printedContent.append("}");
        return printedContent.toString();
    }

    private static void copyToClipboard(String content) {
        StringSelection selection = new StringSelection(content);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
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

    private static String generateRg() {
        Random random = new Random();
        StringBuilder rg = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            rg.append(random.nextInt(10));
            if (i == 2 || i == 5) {
                rg.append(".");
            } else if (i == 8) {
                rg.append("-");
            }
        }

        return rg.toString();
    }

    private static String generateAddress() {
        String[] streets = {"Rua A", "Avenida B", "Praça C", "Travessa D", "Alameda E"};
        String[] cities = {"City1", "City2", "City3", "City4", "City5"};
        Random random = new Random();
        return streets[random.nextInt(streets.length)] + ", " + random.nextInt(100) + ", " + cities[random.nextInt(cities.length)];
    }

    private static String generateCep() {
        Random random = new Random();
        StringBuilder cep = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            cep.append(random.nextInt(10));
            if (i == 4) {
                cep.append("-");
            }
        }

        return cep.toString();
    }

    private static int generateNumber() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    private static String generateState() {
        String[] states = {"State1", "State2", "State3", "State4", "State5"};
        Random random = new Random();
        return states[random.nextInt(states.length)];
    }

    private static String generateCountry() {
        String[] countries = {"Country1", "Country2", "Country3", "Country4", "Country5"};
        Random random = new Random();
        return countries[random.nextInt(countries.length)];
    }
}
