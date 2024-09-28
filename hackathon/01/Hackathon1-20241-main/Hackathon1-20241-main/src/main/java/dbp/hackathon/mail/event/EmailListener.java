package dbp.hackathon.mail.event;

import dbp.hackathon.mail.domain.EmailService;
import dbp.hackathon.ticket.event.TicketEvent;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    @Async
    public void handleTicketEventListener(
            TicketEvent ticketEvent
    )
            throws MessagingException {
        emailService.sendHTMLEmail(ticketEvent.getTicket());
    }


}