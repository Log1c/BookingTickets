package ua.logic.bookingTicket.entity;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Movie {
    @Id
    private String id;

    private String title;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
        this.id = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
