package org.example.hotel_reservation_system.services.EmailServices.Employees;

import jakarta.mail.internet.MimeMessage;
import org.example.hotel_reservation_system.Enum.Cargo.CargoEmployees;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeSuccessfullyHired{
    private final JavaMailSender mailSender;
    public EmployeeSuccessfullyHired(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${support.mail}")
    private String supportEmail;

    public void SendEmailEmployeeHired(String email, String nome, CargoEmployees cargo) {
        try{
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mail);
            messageHelper.setSubject("Você foi contratado como "+cargo+" em Empresa fic");
            messageHelper.setText(getMessage(nome, cargo), true);
            messageHelper.setFrom(supportEmail);
            messageHelper.setTo(email);
            mailSender.send(mail);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getMessage(String nome, CargoEmployees cargo) {
        return "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Contratação</title>" +
                "    <style>" +
                "        body {" +
                "            font-family: Arial, sans-serif;" +
                "            margin: 0;" +
                "            padding: 0;" +
                "        }" +
                "        .container {" +
                "            max-width: 600px;" +
                "            margin: 20px auto;" +
                "            padding: 20px;" +
                "            border: 1px solid #ccc;" +
                "            border-radius: 5px;" +
                "            background-color: #f9f9f9;" +
                "        }" +
                "        h2 {" +
                "            color: #333;" +
                "        }" +
                "        p {" +
                "            color: #555;" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"container\">" +
                "        <h2>Parabéns!</h2>" +
                "        <p>Você foi contratado como <strong>" + cargo + "</strong> em uma empresa fic </p>" +
                "        <p>Agradecemos por se juntar à nossa equipe!</p>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }

}
