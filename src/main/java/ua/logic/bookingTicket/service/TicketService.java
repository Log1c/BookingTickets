package ua.logic.bookingTicket.service;

import org.springframework.stereotype.Service;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public interface TicketService {
    Collection<Ticket> getTickets();

    Ticket addTicket(Ticket ticket);

    Optional<Ticket> getTicket(String id);

    Collection<Ticket> getTickets(Collection<String> ids);

    Collection<Ticket> getAvailableTickets(TicketFilter filter);

    Collection<BookedTicket> getBookedTickets(String userId, TicketFilter filter);

    List<BookedTicket> bookTickets(String userId, Collection<String> bookedTickets);
}
