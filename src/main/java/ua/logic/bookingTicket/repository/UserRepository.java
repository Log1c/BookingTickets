package ua.logic.bookingTicket.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import ua.logic.bookingTicket.entity.User;

public interface UserRepository extends Neo4jRepository<User, Long> {
    User findByName(String name);
}
