package ua.logic.bookingTicket.service;

import org.springframework.stereotype.Service;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class DefaultTicketService implements TicketService {
    private Set<Ticket> tickets = new HashSet<>();
    private Set<BookedTicket> bookedTickets = new HashSet<>();

    @Override
    public Collection<Ticket> getTickets() {
        return Collections.unmodifiableCollection(tickets);
    }

    @Override
    public Ticket getTicket(String id) {
        return tickets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst().get();
    }

    @Override
    public Collection<Ticket> getAvailableTickets(TicketFilter filter) {
        Set<String> collect = bookedTickets.stream()
                .map(b -> b.getId())
                .collect(Collectors.toSet());

        Set<Ticket> tickets = this.tickets.stream()
                .filter(t -> !collect.contains(t.getId()))
                .collect(Collectors.toSet());

        Stream<Ticket> stream = tickets.stream();
        if (filter.getTitle().isPresent()) {
            stream = stream.filter(t -> t.getTitle().contains(filter.getTitle().get()));
        }

        if (filter.getPlace().isPresent()) {
            stream = stream.filter(t -> t.getPlace().equals(filter.getPlace().get()));
        }

        if (filter.getCategory().isPresent()) {
            stream = stream.filter(t -> t.getCategory().equals(filter.getCategory().get()));
        }

        if (filter.getDate().isPresent()) {
            stream = stream.filter(t -> t.getDate().equals(filter.getDate().get()));
        }

        return stream.collect(Collectors.toList());
    }

    @Override
    public Collection<BookedTicket> getBookedTickets() {
        return new HashSet<>(bookedTickets);
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        tickets.add(ticket);

        return ticket;
    }

    @Override
    public List<BookedTicket> bookTickets(String userId, Collection<String> ticketsIds) {
        //TODO adding check on existence tickets

        List<BookedTicket> result = ticketsIds.stream()
                .map(b -> new BookedTicket(b, userId))
                .collect(Collectors.toList());

        bookedTickets.addAll(result);

        return result;
    }
}
