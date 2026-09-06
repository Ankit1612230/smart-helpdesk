package ticket_service.ticket_service.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message){
        super(message);
    }
}
