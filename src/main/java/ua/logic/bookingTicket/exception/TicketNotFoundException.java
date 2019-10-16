package ua.logic.bookingTicket.exception;

import java.util.Collection;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String ticketId) {
        super("Could not find ticket with id = " + ticketId);
    }

    public TicketNotFoundException(Collection<String> ticketIds) {
        super("Could not find ticket all ticket with id = " + ticketIds);
    }
}
