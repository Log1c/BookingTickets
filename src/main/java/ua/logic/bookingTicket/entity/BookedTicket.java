package ua.logic.bookingTicket.entity;

public class BookedTicket {
    private final String bookedTicketId;
    private final String userId;
    private final Ticket ticket;

    public BookedTicket(String bookedTicketId, String userId, Ticket ticket) {
        this.bookedTicketId = bookedTicketId;
        this.userId = userId;
        this.ticket = ticket;
    }

    public String getId() {
        return bookedTicketId;
    }

    public String getUserId() {
        return userId;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
