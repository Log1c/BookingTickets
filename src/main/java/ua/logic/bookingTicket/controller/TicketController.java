package ua.logic.bookingTicket.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ua.logic.bookingTicket.TicketCategory;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.service.TicketService;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public Collection<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTickets(@PathVariable("id") String id) {
        return ticketService.getTicket(id);
    }

    @GetMapping("/available")
    public Collection<Ticket> getAvailableTickets(
            @RequestParam(name = "title", required = false) String title
            , @RequestParam(name="place", required = false) Integer place
            , @RequestParam(name="category", required = false) TicketCategory category
            , @RequestParam(name="date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date date) {

        TicketFilter.Builder builder = new TicketFilter.Builder()
                .title(title)
                .place(place)
                .ticketCategory(category)
                .date(date);

        return ticketService.getAvailableTickets(builder.build());
    }

    @PostMapping
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }
}
