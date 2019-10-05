package ua.logic.bookingTicket.repository;

import org.springframework.stereotype.Repository;
import ua.logic.bookingTicket.entity.BookedTicket;

import java.util.Collection;

@Repository
public interface BookedTicketRepository {
    Collection<BookedTicket> findAll();

    void save(Collection<BookedTicket> bookedTickets);
}
