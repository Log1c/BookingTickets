package ua.logic.bookingTicket.repository;

import org.springframework.stereotype.Repository;
import ua.logic.bookingTicket.entity.Ticket;

import java.util.*;
import java.util.stream.Collectors;

@Repository
class DefaultTicketRepository implements TicketRepository {
    private Set<Ticket> tickets = new HashSet<>();

    @Override
    public Optional<Ticket> findOne(String id) {
        return tickets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<Ticket> findAll() {
        return Collections.unmodifiableCollection(tickets);
    }

    @Override
    public Collection<Ticket> findAll(Collection<String> ids) {
        return tickets.stream()
                .filter(t -> ids.contains(t.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Ticket save(Ticket ticket) {
        tickets.add(ticket);
        return null;
    }
}
