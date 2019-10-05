package ua.logic.bookingTicket.repository;

import org.springframework.stereotype.Repository;
import ua.logic.bookingTicket.entity.BookedTicket;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class DefaultBookedTicketRepository implements BookedTicketRepository{
    private Set<BookedTicket> bookedTickets = new HashSet<>();

    @Override
    public Collection<BookedTicket> findAll() {
        return Collections.unmodifiableCollection(bookedTickets);
    }

    @Override
    public void save(Collection<BookedTicket> bookedTickets) {
        this.bookedTickets.addAll(bookedTickets);
    }
}
