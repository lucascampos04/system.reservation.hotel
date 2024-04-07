package org.example.hotel_reservation_system.services.EmailServices.PaymentsSuccess;

import jakarta.mail.internet.MimeMessage;
import org.example.hotel_reservation_system.Enum.formaPagamento.FormaDePagemntoEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationPaymentSuccessServices {
    private final JavaMailSender mailSender;

    @Value("${support.mail}")
    private String supportMail;
    public NotificationPaymentSuccessServices(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void SendEmailPaymentCongratulations(String nome, Double valor, String email, FormaDePagemntoEnum formaDePagemntoEnum) {
        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mail);
            messageHelper.setSubject("Reserva Concluída");
            messageHelper.setText(getMessageMail(nome, valor, formaDePagemntoEnum), true);
            messageHelper.setFrom(supportMail);
            messageHelper.setTo(email);
            mailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getMessageMail(String nome, Double valor, FormaDePagemntoEnum formaDePagemntoEnum) {
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
                "        .success-message {\n" +
                "            background-color: #d4edda;\n" +
                "            border-color: #c3e6cb;\n" +
                "            color: #155724;\n" +
                "            padding: 10px;\n" +
                "            border-radius: 5px;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <h2>Reserva Concluída</h2>\n" +
                "        <div class=\"success-message\">\n" +
                "            <p>Ola <span style=\"font-weight: bold;\">" + nome + "</span>,</p>\n" +
                "            <p>Parabéns! Seu pagamento foi realizado com sucesso.</p>\n" +
                "            <p>O valor da reserva foi de <span style=\"font-weight: bold;\">R$ " + valor + "</span>.</p>\n" +
                "            <p>O pagamento foi efetuado utilizando a forma de pagamento: <span style=\"font-weight: bold;\">" + formaDePagemntoEnum + "</span>.</p>\n" +
                "        </div>\n" +
                "        <p>Obrigado por escolher nossos serviços. Tenha uma ótima estadia!</p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
