package ua.logic.bookingTicket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.logic.bookingTicket.entity.BookedTicket;

@Repository
public interface BookedTicketRepository extends CrudRepository<BookedTicket, String> {
}
