package ua.logic.bookingTicket.service;

import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.exception.TicketNotFoundException;
import ua.logic.bookingTicket.repository.BookedTicketRepository;
import ua.logic.bookingTicket.repository.TicketRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

class DefaultTicketService implements TicketService {
    protected final TicketRepository ticketRepository;
    protected final BookedTicketRepository bookedTicketRepository;

    DefaultTicketService(TicketRepository ticketRepository,
                         BookedTicketRepository bookedTicketRepository) {
        this.ticketRepository = ticketRepository;
        this.bookedTicketRepository = bookedTicketRepository;
    }

    @Override
    public Collection<Ticket> getTickets() {
        return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Ticket> getTicket(String id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Collection<Ticket> getTickets(Collection<String> ids) {
        return StreamSupport.stream(ticketRepository.findAllById(ids).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Ticket> getAvailableTickets(TicketFilter filter) {
        Set<String> ids = StreamSupport.stream(bookedTicketRepository.findAll().spliterator(), false)
                .map(BookedTicket::getId)
                .collect(Collectors.toSet());

        Set<Ticket> tickets = StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
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
        Set<String> bookedTicketsIds = StreamSupport.stream(bookedTicketRepository.findAll().spliterator(), false)
                .map(BookedTicket::getId)
                .collect(Collectors.toSet());

        Set<Ticket> tickets = StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
                .filter(t -> bookedTicketsIds.contains(t.getId()))
                .collect(Collectors.toSet());

        Stream<Ticket> filteredTickets = getTicketFilter(tickets, filter);

        Set<String> filteredTicketIds = filteredTickets.map(Ticket::getId)
                .collect(Collectors.toSet());

        return StreamSupport.stream(bookedTicketRepository.findAll().spliterator(), false)
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
                .map(b -> new BookedTicket(b.getId(), userId, b.getId()))
                .collect(Collectors.toList());

        bookedTicketRepository.saveAll(result);

        return result;
    }
}
