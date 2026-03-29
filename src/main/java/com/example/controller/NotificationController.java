package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import com.example.service.EmailService;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void send(@RequestParam String email) {
        emailService.send(email, "Тестовое сообщение");
    }
}
