package com.ai_service.ai_service.listener;

import com.ai_service.ai_service.dto.TicketAnalysis;
import com.ai_service.ai_service.event.TicketCreatedEvent;
import com.ai_service.ai_service.event.TicketEnrichedEvent;
import com.ai_service.ai_service.event.TicketEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class TicketEventListener {

    private final ChatClient chatClient;
    private final TicketEventProducer ticketEventProducer;

    public TicketEventListener(ChatClient.Builder chatClientBuilder, TicketEventProducer ticketEventProducer) {
        this.chatClient = chatClientBuilder.build();
        this.ticketEventProducer = ticketEventProducer;
    }

    @KafkaListener(topics = "ticket-created", groupId = "ai-service-group")
    public void handleTicketCreated(TicketCreatedEvent event) {
        log.info("Received ticket event: {}", event);

        String prompt = """
                You are a support ticket triage assistant.
                Analyze this support ticket and respond only with the requested fields.

                Title: %s
                Description: %s

                Classify category as exactly one of: BILLING, TECHNICAL, ACCOUNT, GENERAL, BUG_REPORT.
                Classify sentiment as exactly one of: POSITIVE, NEUTRAL, NEGATIVE.
                Write a short, professional suggested reply (2-3 sentences).
                """.formatted(event.getTitle(), event.getDescription());

        TicketAnalysis analysis = chatClient.prompt()
                .user(prompt)
                .call()
                .entity(TicketAnalysis.class);

        log.info("AI analysis for ticket {}: {}", event.getTicketId(), analysis);

        ticketEventProducer.publishTicketEnriched(
                new TicketEnrichedEvent(event.getTicketId(), analysis.category(), analysis.sentiment(), analysis.suggestedReply())
        );
    }
}