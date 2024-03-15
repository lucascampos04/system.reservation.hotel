package org.example.hotel_reservation_system.services.EmailServices.Client;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.charset.StandardCharsets;

@Service
public class NotificationClientInsert {
    private final JavaMailSender mailSender;
    public NotificationClientInsert(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${support.mail}")
    private String supportMail;

    public void SendEmailOfClientCreate(String email, String nome, String plano){
        try{
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mail);
            messageHelper.setSubject("Cliente Criado");
            messageHelper.setText(getMessageMail(nome, plano), true);
            messageHelper.setFrom(supportMail);
            messageHelper.setTo(email);
            mailSender.send(mail);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private String getMessageMail(String nome, String plano){

        if (plano == null){
            plano = "Sem Plano";
        }

        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Client Email Template</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h2>Olá "+ nome +",</h2>\n" +
                "    <p>Seja bem-vindo ao nosso serviço! Você foi registrado com sucesso.</p>\n" +
                "    <p>Plano selecionado: " + plano + "</p>\n" +
                "    <p>Obrigado por escolher nossos serviços.</p>\n" +
                "</body>\n" +
                "</html>\n";
    }
}
