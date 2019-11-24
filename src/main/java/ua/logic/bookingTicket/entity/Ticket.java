package ua.logic.bookingTicket.entity;

//import org.springframework.data.annotation.Id;
import ua.logic.bookingTicket.TicketCategory;

import java.util.Date;

public class Ticket {
//    @Id
    private final String id;
    private String title;
    private Date date;    // Date when this film is demonstrated
    private TicketCategory category;
    private Integer place; // Place number

    public Ticket(String id, String title, Date date, TicketCategory category, Integer place) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.category = category;
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public TicketCategory getCategory() {
        return category;
    }

    public Integer getPlace() {
        return place;
    }
}
