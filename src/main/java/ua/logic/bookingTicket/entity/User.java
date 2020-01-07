package ua.logic.bookingTicket.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "WATCHED")
    private Set<Movie> movies = new HashSet<>();

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public void watch(Movie movie) {
        movies.add(movie);
    }
}
