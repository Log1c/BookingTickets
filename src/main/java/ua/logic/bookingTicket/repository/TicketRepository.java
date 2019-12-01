package ua.logic.bookingTicket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.logic.bookingTicket.entity.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, String>, CustomizeTicketRepository<Ticket> {
}
