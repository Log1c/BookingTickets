package ua.logic.bookingTicket.controller;

import org.springframework.web.bind.annotation.*;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.service.TicketService;

import java.util.Collection;
import java.util.Optional;

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

    @PostMapping
    public BookedTicket addTicket(@RequestBody BookedTicket bookedTicket) {
        Optional<BookedTicket> result = ticketService.bookTicket(bookedTicket);
        return result.orElseThrow(IllegalArgumentException::new);
    }

    //TODO Request body should contain JSON list of ticket ids to be booked.
//    @PostMapping
//    public Collection<BookedTicket> addTickets(@RequestBody Collection<BookedTicket> bookedTickets) {
//        return ticketService.bookTickets(bookedTickets);
//    }

//    @PutMapping("/{id}/book")
//    public BookedTicket bookTicket(@PathVariable("id") String id) {
//        return ticketService.bookTicket(id);
//    }
}
