package ticket_service.ticket_service.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ticket_service.ticket_service.dto.TicketRequest;
import ticket_service.ticket_service.dto.TicketResponse;
import ticket_service.ticket_service.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor


public class TicketController {
    private final TicketService ticketService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse createTicket(@Valid @RequestBody TicketRequest request){
        return ticketService.createTicket(request);
    }
    @GetMapping
    public List<TicketResponse> getAllTickets(){
        return ticketService.getAllTickets();
    }
    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable Long id){
        return ticketService.getTicketById(id);
    }
    @PutMapping("/{id}")
    public TicketResponse updateTicket(@PathVariable Long id,@Valid @RequestBody TicketRequest request){
        return ticketService.updateTicket(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable Long id){
        ticketService.deleteTicket(id);
    }


}
