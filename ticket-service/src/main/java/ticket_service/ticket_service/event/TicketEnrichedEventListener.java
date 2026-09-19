package ticket_service.ticket_service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ticket_service.ticket_service.entity.Ticket;
import ticket_service.ticket_service.entity.TicketCategory;
import ticket_service.ticket_service.repository.TicketRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class TicketEnrichedEventListener {

    private final TicketRepository ticketRepository;

    @KafkaListener(topics = "ticket-enriched", groupId = "ticket-service-group")
    public void handleTicketEnriched(TicketEnrichedEvent event) {
        log.info("Received enriched data for ticket {}", event.getTicketId());

        ticketRepository.findById(event.getTicketId()).ifPresentOrElse(ticket -> {
            ticket.setCategory(TicketCategory.valueOf(event.getCategory()));
            ticket.setSentiment(event.getSentiment());
            ticket.setSuggestedReply(event.getSuggestedReply());
            ticketRepository.save(ticket);
            log.info("Ticket {} updated with AI analysis", ticket.getId());
        }, () -> log.warn("Ticket {} not found, skipping enrichment", event.getTicketId()));
    }
}