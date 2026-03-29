package com.example.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.event.UserEvent;
import com.example.service.EmailService;

@Service
public class KafkaConsumerService {

    private final EmailService emailService;

    public KafkaConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-topic", groupId = "notification-group")
    public void listen(UserEvent event) {

        System.out.println("Получено событие: " + event.getAction());

        if ("CREATED".equals(event.getAction())) {
            emailService.send(
                    event.getEmail(),
                    "Здравствуйте! Ваш аккаунт был успешно создан."
            );
        }

        if ("DELETED".equals(event.getAction())) {
            emailService.send(
                    event.getEmail(),
                    "Здравствуйте! Ваш аккаунт был удалён."
            );
        }
    }
}