package ticket_service.ticket_service.dto;

import lombok.Builder;
import lombok.Data;
import ticket_service.ticket_service.entity.TicketCategory;
import ticket_service.ticket_service.entity.TicketStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketResponse {
    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketCategory category;
    private String createdBy;
    private LocalDateTime createdAt;

}
