package ua.logic.bookingTicket.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookedTicket")
public class BookedTicket {
    @Id
    private String bookedTicketId;
    private String userId;
    private String ticketId;

    @SuppressWarnings("unused") //for JPA
    public BookedTicket() {
    }

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
