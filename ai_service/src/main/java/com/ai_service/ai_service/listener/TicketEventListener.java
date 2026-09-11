package com.ai_service.ai_service.listener;

import com.ai_service.ai_service.event.TicketCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TicketEventListener {

    @KafkaListener(topics = "ticket-created", groupId = "ai-service-group")
    public void handleTicketCreated(TicketCreatedEvent event) {
        log.info("Received ticket event: {}", event);
    }
}