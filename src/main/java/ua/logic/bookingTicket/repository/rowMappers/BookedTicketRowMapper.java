package ua.logic.bookingTicket.repository.rowMappers;

import org.springframework.jdbc.core.RowMapper;
import ua.logic.bookingTicket.entity.BookedTicket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookedTicketRowMapper implements RowMapper<BookedTicket> {
    @Override
    public BookedTicket mapRow(ResultSet resultSet, int i) throws SQLException {
        return new BookedTicket(
                resultSet.getString("bookedTicketId"),
                resultSet.getString("userId"),
                resultSet.getString("ticketId")
        );
    }
}
