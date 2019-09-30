package ua.logic.bookingTicket.service;

import org.springframework.stereotype.Service;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;

import java.util.Collection;
import java.util.Optional;

@Service
public interface TicketService {
    Collection<Ticket> getTickets();

    Ticket getTicket(String id);

    Collection<Ticket> getAvailableTickets(TicketFilter filter);

    Collection<BookedTicket> getBookedTickets();

    Ticket addTicket(Ticket ticket);

    Optional<BookedTicket> bookTicket(BookedTicket bookedTicket);

    Collection<BookedTicket> bookTickets(Collection<BookedTicket> bookedTickets);
}
