package dbp.hackathon.ticket.event;

import dbp.hackathon.ticket.domain.Ticket;
import org.springframework.context.ApplicationEvent;

public class TicketCreatedEvent extends ApplicationEvent {
    private final Ticket ticket;


    public TicketCreatedEvent(Ticket source) {
        super(source);
        this.ticket = source;
    }
}
