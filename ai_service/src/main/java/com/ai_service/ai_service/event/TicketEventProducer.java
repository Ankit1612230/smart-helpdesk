package com.ai_service.ai_service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "ticket-enriched";

    public void publishTicketEnriched(TicketEnrichedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}