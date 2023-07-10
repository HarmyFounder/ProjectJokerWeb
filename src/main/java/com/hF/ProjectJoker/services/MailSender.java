package com.hF.ProjectJoker.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Service
public class MailSender {

    final String from = "ofbrick121@Gmail.com";

    String host = "smtp.google.com";
    String smtpPort = "465";

    Properties properties = new Properties();

    {
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
    }

    Session session = Session.getInstance(properties,
            new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, "qzxhxzfbtplttsat");
                }
            });


    public void send(String recipientGmail, String text, int duration) throws MessagingException, InterruptedException {

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(from));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientGmail));
        message.setSubject("АНЕКДОТ ЧТОБЫ ВЗОРВАТЬСЯ !!!! УРА АНЕКДОТ КАЖДЫЕ " + duration + " СЕКУНДs");
        message.setText(text);

        Transport.send(message);
        TimeUnit.SECONDS.sleep(duration);


    }


}
