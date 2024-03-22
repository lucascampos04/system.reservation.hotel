package org.example.hotel_reservation_system.test.GeneratorJson;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Random;

public class GeneratorJson {
    static String email = "camposdlucasoli@gmail.com";
    public static void main(String[] args) {
        String json = generateJson();
        System.out.println(json);
        copyToClipboard(json);
    }

    private static String generateJson() {
        return "{\n" +
                "  \"nome\": \"" + generateName() + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"cpf\": \"" + generateCpf() + "\",\n" +
                "  \"rg\": \"" + generateRg() + "\",\n" +
                "  \"endereco\": \"" + generateAddress() + "\",\n" +
                "  \"cep\": \"" + generateCep() + "\",\n" +
                "  \"numero\": " + generateNumber() + ",\n" +
                "  \"estado\": \"" + generateState() + "\",\n" +
                "  \"pais\": \"" + generateCountry() + "\",\n" +
                "  \"data_nascimento\": \"29/06/2004\",\n" +
                "  \"plano\": \"BASICO\"\n" +
                "}";
    }

    private static String generateName() {
        String[] prefixes = {"John", "David", "Michael", "Sarah", "Emily", "Emma", "James", "William"};
        String[] suffixes = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"};

        Random random = new Random();

        String prefix = prefixes[random.nextInt(prefixes.length)];
        String suffix = suffixes[random.nextInt(suffixes.length)];

        return prefix + " " + suffix;
    }

    private static String generateCpf() {
        Random random = new Random();
        StringBuilder cpf = new StringBuilder();

        for (int i = 1; i <= 11; i++) {
            cpf.append(random.nextInt(10));
        }

        return cpf.toString();
    }
    private static String generateRg() {
        Random random = new Random();
        StringBuilder rg = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            rg.append(random.nextInt(10));
        }

        return rg.toString();
    }

    private static String generateAddress() {
        String[] streets = {"Rua A", "Avenida B", "Praça C", "Travessa D", "Alameda E"};
        String[] cities = {"City", "City", "City", "City", "City"};
        Random random = new Random();
        return streets[random.nextInt(streets.length)] + ", " + random.nextInt(100) + ", " + cities[random.nextInt(cities.length)];
    }

    private static String generateCep() {
        Random random = new Random();
        StringBuilder cep = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            cep.append(random.nextInt(10));
        }

        return cep.toString();
    }

    private static int generateNumber() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    private static String generateState() {
        String[] states = {"State", "State", "State", "State", "State"};
        Random random = new Random();
        return states[random.nextInt(states.length)];
    }

    private static String generateCountry() {
        String[] countries = {"Country", "Country", "Country", "Country", "Country"};
        Random random = new Random();
        return countries[random.nextInt(countries.length)];
    }

    private static void copyToClipboard(String content) {
        StringSelection selection = new StringSelection(content);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
