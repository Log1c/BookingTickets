package ua.logic.bookingTicket.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.repository.rowMappers.TicketRowMapper;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
class DefaultTicketRepository implements TicketRepository {
    private final JdbcTemplate jdbcTemplate;

    public DefaultTicketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(readOnly=true)
    public Optional<Ticket> findOne(String id) {
        try {//TODO be out of catch Exception
            return Optional.of(jdbcTemplate.queryForObject(
                    "SELECT * FROM ticket WHERE id=?",
                    new Object[]{id}, new TicketRowMapper()));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly=true)
    public Collection<Ticket> findAll() {
        return jdbcTemplate.query("SELECT * FROM ticket", new TicketRowMapper());
    }

    @Override
    @Transactional(readOnly=true)
    public Collection<Ticket> findAll(Collection<String> ids) {
        List<Ticket> tickets = jdbcTemplate.query("SELECT * FROM ticket", new TicketRowMapper());

        return tickets.stream()//TODO change it for performance
                .filter(t -> ids.contains(t.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Ticket save(Ticket ticket) {
        Optional<Ticket> one = findOne(ticket.getId());

        if (one.isPresent()) {
            return update(ticket);
        }

        return create(ticket);
    }

    private Ticket create(Ticket ticket) {
        jdbcTemplate.update(
                "INSERT INTO ticket (id, title, date, category, place) values(?,?,?,?,?)",
                ticket.getId(), ticket.getTitle(), ticket.getDate(), ticket.getCategory().toString(), ticket.getPlace());

        return ticket;
    }

    private Ticket update(Ticket ticket) {
        jdbcTemplate.update(
                "UPDATE ticket SET title = ?, date = ?, category = ?, place = ? where id = ?",
                ticket.getId(), ticket.getTitle(), ticket.getDate(), ticket.getCategory(), ticket.getPlace());

        return ticket;
    }
}
