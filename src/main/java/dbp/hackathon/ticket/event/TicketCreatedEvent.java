package dbp.hackathon.ticket.event;

import dbp.hackathon.ticket.domain.Ticket;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TicketCreatedEvent extends ApplicationEvent {
    private final Ticket ticket;


    public TicketCreatedEvent(Ticket source) {
        super(source);
        this.ticket = source;
    }
}
