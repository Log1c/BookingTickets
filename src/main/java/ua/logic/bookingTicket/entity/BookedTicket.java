package ua.logic.bookingTicket.entity;

public class BookedTicket {
    private final String bookedTicketId;
    private final String userId;
    private final String ticketId;

    public BookedTicket(String bookedTicketId, String userId, String ticketId) {
        this.bookedTicketId = bookedTicketId;
        this.userId = userId;
        this.ticketId = ticketId;
    }

    public String getId() {
        return bookedTicketId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTicketId() {
        return ticketId;
    }
}
