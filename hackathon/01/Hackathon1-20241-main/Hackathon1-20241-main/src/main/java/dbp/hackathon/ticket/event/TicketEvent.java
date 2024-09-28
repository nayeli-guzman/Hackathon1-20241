package dbp.hackathon.ticket.event;

import dbp.hackathon.ticket.domain.Ticket;
import org.springframework.context.ApplicationEvent;

public class TicketEvent extends ApplicationEvent {

    private final Ticket ticket;

    public TicketEvent(Ticket source) {
        super(source);
        this.ticket = source;
    }

    public Ticket getTicket(){
        return ticket;
    }
}
