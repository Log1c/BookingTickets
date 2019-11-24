package ua.logic.bookingTicket.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.repository.rowMappers.BookedTicketRowMapper;

import java.util.Collection;

@Repository
public class DefaultBookedTicketRepository implements BookedTicketRepository {
    private final JdbcTemplate jdbcTemplate;

    public DefaultBookedTicketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<BookedTicket> findAll() {
        return jdbcTemplate.query("SELECT * FROM bookedTicket", new BookedTicketRowMapper());
    }

    @Override
    //without update
    @Transactional
    public void save(Collection<BookedTicket> bookedTickets) {
        bookedTickets.forEach(this::create);
    }

    private BookedTicket create(BookedTicket bookedTicket) {
        jdbcTemplate.update(
                "INSERT INTO bookedTicket (bookedTicketId, userId, ticketId) values(?,?,?)",
                bookedTicket.getId(), bookedTicket.getUserId(), bookedTicket.getTicketId());

        return bookedTicket;
    }
}