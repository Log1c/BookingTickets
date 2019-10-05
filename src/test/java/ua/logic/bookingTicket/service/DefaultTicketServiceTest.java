package ua.logic.bookingTicket.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.logic.bookingTicket.TicketCategory;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DefaultTicketServiceTest {
    @Autowired
    private TicketService ticketService;

    @Test
    public void getAvailableTickets() {
        ticketService.addTicket(createTicket());
        assertEquals(ticketService.getAvailableTickets(new TicketFilter.Builder().build()).size(), 1);
    }

    @Test
    public void getBookedTicketsWithoutFilter() {
        ticketService.addTicket(createTicket());
        String userId = "u1";
        ticketService.bookTickets(userId, Arrays.asList("0"));
        assertEquals(1, ticketService.getBookedTickets(userId, new TicketFilter.Builder().build()).size());
    }

    @Test
    public void getBookedTicketsWithFilterWithoutResult() {
        ticketService.addTicket(createTicket());
        String userId = "u1";
        ticketService.bookTickets(userId, Arrays.asList("0"));
        TicketFilter.Builder builder = new TicketFilter.Builder().id("1");
        assertEquals(0, ticketService.getBookedTickets(userId, builder.build()).size());
    }

    @Test
    public void getBookedTicketsWithFilterWithResult() {
        ticketService.addTicket(createTicket());
        String userId = "u1";
        ticketService.bookTickets(userId, Arrays.asList("0"));
        TicketFilter.Builder builder = new TicketFilter.Builder().id("0");
        assertEquals(1, ticketService.getBookedTickets(userId, builder.build()).size());
    }

    private Ticket createTicket() {
        return new Ticket("0", "A Beautiful Mind", new Date(), TicketCategory.STANDARD, 1);
    }

    private BookedTicket createBookTicket() {
        return new BookedTicket("0", "u1");
    }
}