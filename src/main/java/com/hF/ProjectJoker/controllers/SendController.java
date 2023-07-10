package com.hF.ProjectJoker.controllers;

import com.hF.ProjectJoker.services.JokeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/jokes")
public class SendController {

    private final JokeSender jokeSender;

    @Autowired
    public SendController(JokeSender jokeSender) {
        this.jokeSender = jokeSender;
    }

    @GetMapping("/")
    public String getMainPage() {
        return "main";
    }

    @PostMapping("/")
    public String sendJokes(@RequestParam String attributeName, String className, String url, String recipientGmail, int duration) {
        jokeSender.send(attributeName, className, url, recipientGmail, duration);
        return "main";
    }

}
