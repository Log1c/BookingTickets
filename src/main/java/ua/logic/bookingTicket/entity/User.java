package ua.logic.bookingTicket.entity;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User {
    @Id
    private String id;

    private String name;

    @Relationship(type = "WATCHED")
    private Set<Movie> movies = new HashSet<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.id = name;
    }

    public void watch(Movie movie) {
        movies.add(movie);
    }
}
