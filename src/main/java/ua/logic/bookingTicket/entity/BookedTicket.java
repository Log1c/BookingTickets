package ua.logic.bookingTicket.entity;

public class BookedTicket {
    private final String bookedTicketId;
    private final String userId;

    public BookedTicket(String bookedTicketId, String userId) {
        this.bookedTicketId = bookedTicketId;
        this.userId = userId;
    }

    public String getId() {
        return bookedTicketId;
    }

    public String getUserId() {
        return userId;
    }
}
