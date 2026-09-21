package com.notification_service.notification_service.listener;

import com.notification_service.notification_service.event.TicketEnrichedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotificationEventListener {

    @KafkaListener(topics = "ticket-enriched", groupId = "notification-service-group")
    public void handleTicketEnriched(TicketEnrichedEvent event) {
        log.info("Sending notification email to {} for ticket {}", event.getCreatedBy(), event.getTicketId());
        log.info("Subject: We've received your ticket ({})", event.getCategory());
        log.info("Body: {}", event.getSuggestedReply());
    }
}