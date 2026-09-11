package ticket_service.ticket_service.service;
import ticket_service.ticket_service.dto.TicketRequest;
import ticket_service.ticket_service.dto.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ticket_service.ticket_service.entity.Ticket;
import ticket_service.ticket_service.event.TicketCreatedEvent;
import ticket_service.ticket_service.event.TicketEventProducer;
import ticket_service.ticket_service.exception.TicketNotFoundException;
import ticket_service.ticket_service.repository.TicketRepository;
import ticket_service.ticket_service.entity.TicketStatus;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketEventProducer ticketEventProducer;
    public TicketResponse createTicket(TicketRequest request){
        Ticket ticket=Ticket.builder().title(request.getTitle()).description(request.getDescription()).
                createdBy(request.getCreatedBy()).build();
        Ticket saved=ticketRepository.save(ticket);
        ticketEventProducer.publishTicketCreated(
                new TicketCreatedEvent(saved.getId(), saved.getTitle(), saved.getDescription(), saved.getCreatedBy())
        );
        return mapToResponse(saved);
    }
    public List<TicketResponse> getAllTickets(){
        return ticketRepository.findAll().stream().map(this::mapToResponse).toList();
    }
    public TicketResponse getTicketById(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with id: " + id));
        return mapToResponse(ticket);
    }
    public TicketResponse updateTicket(Long id,TicketRequest request){
        Ticket ticket=ticketRepository.findById(id).orElseThrow(()-> new TicketNotFoundException("Ticket Not found with Id"+ id));
        ticket.setTitle(request.getTitle());
        ticket.setDescription(request.getDescription());

        Ticket updated=ticketRepository.save(ticket);
        return mapToResponse(updated);
    }
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException("Ticket not found with id: " + id);
        }
        ticketRepository.deleteById(id);
    }
    private TicketResponse mapToResponse(Ticket ticket){
        return TicketResponse.builder()
                .id(ticket.getId())
                .title(ticket.getTitle())
                .description(ticket.getDescription())
                .status(ticket.getStatus())
                .category(ticket.getCategory())
                .createdBy(ticket.getCreatedBy())
                .createdAt(ticket.getCreatedAt())
                .build();
    }
}
