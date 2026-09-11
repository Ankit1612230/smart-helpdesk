package ticket_service.ticket_service.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "ticket-created";

    public void publishTicketCreated(TicketCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}