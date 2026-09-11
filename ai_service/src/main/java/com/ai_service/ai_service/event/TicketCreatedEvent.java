package com.ai_service.ai_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketCreatedEvent {
    private Long ticketId;
    private String title;
    private String description;
    private String createdBy;
}