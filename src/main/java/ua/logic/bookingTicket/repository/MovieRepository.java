package ua.logic.bookingTicket.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import ua.logic.bookingTicket.entity.Movie;

import java.util.Collection;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {
    Movie findByTitle(@Param("title") String title);

    Collection<Movie> findByTitleLike(@Param("title") String title);

    @Query("MATCH " +
            "(s:Movie)<-[:WATCHED]-" +
            "(c:User)-[:WATCHED]->" +
            "(m:Movie)<-[:WATCHED]-(u:User)" +
            "WHERE u.name={userName}" +
            "RETURN s")
    Collection<Movie> recommendation(@Param("userName") String userName);
}
