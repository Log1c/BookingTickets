package ua.logic.bookingTicket.repository.rowMappers;

import org.springframework.jdbc.core.RowMapper;
import ua.logic.bookingTicket.TicketCategory;
import ua.logic.bookingTicket.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketRowMapper implements RowMapper<Ticket> {
    @Override
    public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Ticket(
                resultSet.getString("id"),
                resultSet.getString("title"),
                resultSet.getDate("date"),
                TicketCategory.valueOf(resultSet.getString("category")),
                resultSet.getInt("place")
        );
    }
}
