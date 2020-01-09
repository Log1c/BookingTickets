package ua.logic.bookingTicket.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.logic.bookingTicket.repository.MovieRepository;
import ua.logic.bookingTicket.repository.UserRepository;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendationTicketServiceTest {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testPersistence() {
        String bookOfEli = "Book of Eli";
        String wallE = "WallE";
        String exmachine = "Exmachine";
        String aboutTime = "About time";
        ticketService.bookTickets("u1", Arrays.asList(bookOfEli, wallE, exmachine, aboutTime));

        assertNotNull(userRepository.findById("u1"));
        assertNotNull(movieRepository.findById(bookOfEli));
        assertNotNull(movieRepository.findById(wallE));
        assertNotNull(movieRepository.findById(exmachine));
        assertNotNull(movieRepository.findById(aboutTime));
    }
}