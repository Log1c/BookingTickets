package ua.logic.bookingTicket.service;

import org.springframework.stereotype.Service;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.exception.TicketNotFoundException;
import ua.logic.bookingTicket.repository.BookedTicketRepository;
import ua.logic.bookingTicket.repository.TicketRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
class DefaultTicketService implements TicketService {
    private final TicketRepository ticketRepository;
    private final BookedTicketRepository bookedTicketRepository;

    DefaultTicketService(TicketRepository ticketRepository,
                         BookedTicketRepository bookedTicketRepository) {
        this.ticketRepository = ticketRepository;
        this.bookedTicketRepository = bookedTicketRepository;
    }

    @Override
    public Collection<Ticket> getTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getTicket(String id) {
        return ticketRepository.findOne(id);
    }

    @Override
    public Collection<Ticket> getTickets(Collection<String> ids) {
        return ticketRepository.findAll(ids);
    }

    @Override
    public Collection<Ticket> getAvailableTickets(TicketFilter filter) {
        Set<String> ids = bookedTicketRepository.findAll().stream()
                .map(BookedTicket::getId)
                .collect(Collectors.toSet());

        Set<Ticket> tickets = ticketRepository.findAll().stream()
                .filter(t -> !ids.contains(t.getId()))
                .collect(Collectors.toSet());

        Stream<Ticket> stream = getTicketFilter(tickets, filter);

        return stream.collect(Collectors.toList());
    }

    private Stream<Ticket> getTicketFilter(Set<Ticket> tickets, TicketFilter filter) {
        Stream<Ticket> stream = tickets.stream();
        if (filter.getId().isPresent()) {
            stream = stream.filter(t -> t.getId().contains(filter.getId().get()));
        }

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

        return stream;
    }

    @Override
    public Collection<BookedTicket> getBookedTickets(String userId, TicketFilter filter) {
        Set<String> bookedTicketsIds = bookedTicketRepository.findAll().stream()
                .map(BookedTicket::getId)
                .collect(Collectors.toSet());

        Set<Ticket> tickets = ticketRepository.findAll().stream()
                .filter(t -> bookedTicketsIds.contains(t.getId()))
                .collect(Collectors.toSet());

        Stream<Ticket> filteredTickets = getTicketFilter(tickets, filter);

        Set<String> filteredTicketIds = filteredTickets.map(Ticket::getId)
                .collect(Collectors.toSet());

        return bookedTicketRepository.findAll().stream()
                .filter(b -> filteredTicketIds.contains(b.getId()))
                .filter(b -> b.getUserId().equals(userId))
                .collect(Collectors.toSet());
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<BookedTicket> bookTickets(String userId, Collection<String> ticketsIds) {
        Collection<Ticket> tickets = getTickets(ticketsIds);

        if (tickets.size() != ticketsIds.size()) {
            new TicketNotFoundException(ticketsIds);
        }

        List<BookedTicket> result = tickets.stream()
                .map(b -> new BookedTicket(b.getId(), userId, b))
                .collect(Collectors.toList());

        bookedTicketRepository.save(result);

        return result;
    }
}
