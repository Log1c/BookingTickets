package ua.logic.bookingTicket.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.repository.rowMappers.TicketRowMapper;

import java.util.*;

@Repository
class DefaultTicketRepository implements TicketRepository {
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DefaultTicketRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ticket> findOne(String id) {
        try {//TODO be out of catch Exception
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(
                    "SELECT * FROM ticket WHERE id=?",
                    new Object[]{id}, new TicketRowMapper())));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Ticket> findAll() {
        return jdbcTemplate.query("SELECT * FROM ticket", new TicketRowMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Ticket> findAll(Collection<String> ids) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", ids);

        return namedParameterJdbcTemplate.query("SELECT * FROM ticket WHERE id IN (:ids)", parameters, new TicketRowMapper());
    }

    @Override
    @Transactional
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
