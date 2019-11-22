package ua.logic.bookingTicket.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.repository.rowMappers.TicketRowMapper;

import java.util.*;
import java.util.stream.Collectors;

@Repository
class DefaultTicketRepository implements TicketRepository {
    private final JdbcTemplate jdbcTemplate;

    public DefaultTicketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Ticket> findOne(String id) {
        return tickets.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();
    }

    @Override
    public Collection<Ticket> findAll() {
        return jdbcTemplate.query("SELECT * FROM ticket", new TicketRowMapper());
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
