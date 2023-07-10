package com.hF.ProjectJoker.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

import static java.awt.SystemColor.text;

@Service
public class JokeSender {

    private final MailSender mailSender;

    @Autowired
    public JokeSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String attributeName, String className, String url, String recipientGmail, int duration) {

        MailSender mailSender = new MailSender();

        Document doc = (Document) Jsoup.connect(url);

        Elements jokes;

        if (className != null && !className.isEmpty()) {
            jokes = doc.getElementsByClass(className);
        } else {
            jokes = doc.getElementsByAttribute(attributeName);
        }

        jokes.forEach(joke -> {
            try {
                mailSender.send(recipientGmail, joke.text(), duration);
            } catch (MessagingException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


    }
}
