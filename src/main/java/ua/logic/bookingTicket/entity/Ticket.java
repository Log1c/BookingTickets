package ua.logic.bookingTicket.entity;

import ua.logic.bookingTicket.TicketCategory;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    private String id;
    private String title;
    private Date date;
    private TicketCategory category;
    private Integer place;
    private boolean deleted;

    @SuppressWarnings("unused") //for JPA
    public Ticket() {
    }

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

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
