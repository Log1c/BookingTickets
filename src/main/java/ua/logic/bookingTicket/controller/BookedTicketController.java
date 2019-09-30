package ua.logic.bookingTicket.controller;

import org.springframework.web.bind.annotation.*;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.service.TicketService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/bookedTickets")
public class BookedTicketController {
    private final TicketService ticketService;

    public BookedTicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public Collection<BookedTicket> getBookedTickets() {
        return ticketService.getBookedTickets();
    }

    @PutMapping("/book/{userId}")
    public List<BookedTicket> bookTicket(@PathVariable("userId") String userId, @RequestBody List<String> ticketIds) {
        return ticketService.bookTickets(userId, ticketIds);
    }
}
