package dbp.hackathon.ticket.infrastructure;

import dbp.hackathon.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Iterable<Ticket> findByEstudianteId(Long estudianteId);
}
