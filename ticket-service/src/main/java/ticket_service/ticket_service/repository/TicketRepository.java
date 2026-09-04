package ticket_service.ticket_service.repository;

import  ticket_service.ticket_service.entity.Ticket;
import  ticket_service.ticket_service.entity.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByCreatedBy(String createdBy);
}