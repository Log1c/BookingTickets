package ua.logic.bookingTicket.service;

import org.springframework.stereotype.Service;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.entity.User;
import ua.logic.bookingTicket.repository.BookedTicketRepository;
import ua.logic.bookingTicket.repository.TicketRepository;
import ua.logic.bookingTicket.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class RecommendationTicketService extends DefaultTicketService {
    private final UserRepository userRepository;
    private final MovieService movieService;

    RecommendationTicketService(TicketRepository ticketRepository,
                                BookedTicketRepository bookedTicketRepository,
                                UserRepository userRepository,
                                MovieService movieService) {
        super(ticketRepository, bookedTicketRepository);
        this.userRepository = userRepository;
        this.movieService = movieService;
    }

    @Override
    public List<BookedTicket> bookTickets(String userId, Collection<String> ticketsIds) {
        User user = userRepository.findById(userId).orElse(userRepository.save(new User(userId)));

        Iterable<Ticket> tickets = ticketRepository.findAllById(ticketsIds);
        tickets.forEach(t -> user.watch(movieService.getOrPersistMovie(t.getTitle())));

        return super.bookTickets(userId, ticketsIds);
    }


}
