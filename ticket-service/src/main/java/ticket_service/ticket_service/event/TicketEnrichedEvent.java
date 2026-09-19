package ticket_service.ticket_service.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEnrichedEvent {
    private Long ticketId;
    private String category;
    private String sentiment;
    private String suggestedReply;
}