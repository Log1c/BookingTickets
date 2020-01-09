package ua.logic.bookingTicket.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;
import ua.logic.bookingTicket.entity.User;

@Repository
public interface UserRepository extends Neo4jRepository<User, String> {
    User findByName(String name);
}
