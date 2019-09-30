package ua.logic.bookingTicket.service;

import org.springframework.stereotype.Service;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;

import java.util.Collection;
import java.util.List;

@Service
public interface TicketService {
    Collection<Ticket> getTickets();

    Ticket addTicket(Ticket ticket);

    Ticket getTicket(String id);

    Collection<Ticket> getAvailableTickets(TicketFilter filter);

    Collection<BookedTicket> getBookedTickets();

    List<BookedTicket> bookTickets(String userId, Collection<String> bookedTickets);
}
