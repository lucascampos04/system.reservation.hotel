package org.example.hotel_reservation_system.services.EmailServices.Reservas;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationReservaCongratulations {
    private final JavaMailSender mailSender;

    public NotificationReservaCongratulations(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${support.mail}")
    private String supportMail;

    public void SendEmailReservaCongratulations(String nome, Double valor, String email) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mail);
            messageHelper.setSubject("Reserva Concluída");
            messageHelper.setText(getMessageMail(nome, valor), true);
            messageHelper.setFrom(supportMail);
            messageHelper.setTo(email);
            mailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getMessageMail(String nome, Double valor) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Reserva Concluída</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            color: #333;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 20px auto;\n" +
                "            padding: 20px;\n" +
                "            background-color: #fff;\n" +
                "            border-radius: 8px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        h2 {\n" +
                "            color: #007bff;\n" +
                "        }\n" +
                "        p {\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h2>Reserva Concluída</h2>\n" +
                "        <p>Ola " + nome + ",</p>\n" +
                "        <p>Parabéns, sua reserva foi concluída com sucesso.</p>\n" +
                "        <p>O valor da reserva foi de R$" + valor + "</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";

    }
}
