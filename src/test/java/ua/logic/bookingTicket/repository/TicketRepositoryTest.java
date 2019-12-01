package ua.logic.bookingTicket.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.logic.bookingTicket.TicketCategory;
import ua.logic.bookingTicket.entity.Ticket;

import java.util.*;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketRepositoryTest {
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    @Transactional
    public void testFindAll() {
        Ticket ticket = createTicket();
        ticketRepository.save(ticket);

        Optional<Ticket> expect = StreamSupport.stream(ticketRepository.findAll().spliterator(), false).findFirst();
        assertTrue(expect.isPresent());
    }

    @Test
    @Transactional
    public void testFindById() {
        Ticket ticket = createTicket();
        ticketRepository.save(ticket);

        Optional<Ticket> expect = ticketRepository.findById(ticket.getId());
        assertTrue(expect.isPresent());
    }

    @Test
    @Transactional
    public void testFindAllById() {
        Ticket ticket = createTicket();
        ticketRepository.save(ticket);

        List<String> ids = Collections.singletonList(ticket.getId());
        Optional<Ticket> expect = StreamSupport.stream(ticketRepository.findAllById(ids).spliterator(), false)
                .findFirst();
        assertTrue(expect.isPresent());
    }

    @Test
    @Transactional
    public void delete() {
        Ticket ticket = createTicket();
        ticketRepository.save(ticket);

        assertEquals(1, ticketRepository.count());
        ticketRepository.delete(ticket);

        assertTrue(ticket.isDeleted());
    }

    private Ticket createTicket() {
        return new Ticket("1", "test title", new Date(), TicketCategory.STANDARD, 1);
    }
}