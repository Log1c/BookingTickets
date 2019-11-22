package ua.logic.bookingTicket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.logic.bookingTicket.entity.Ticket;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TicketRepository {
    Optional<Ticket> findOne(String id);

    Collection<Ticket> findAll();

    Collection<Ticket> findAll(Collection<String> ids);

    Ticket save(Ticket ticket);
}
