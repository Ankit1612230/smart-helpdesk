package ticket_service.ticket_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TicketRequest {
    @NotBlank(message="title is required")
    private String title;
    private String description;
    @NotBlank(message="created by is required")
    private String createdBy;

}
