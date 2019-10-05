package ua.logic.bookingTicket.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ua.logic.bookingTicket.TicketCategory;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.service.TicketService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/bookedTickets")
public class BookedTicketController {
    private final TicketService ticketService;

    public BookedTicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public Collection<BookedTicket> getTickets(
            @RequestParam(name = "userId") String userId
            , @RequestParam(name = "title", required = false) String title
            , @RequestParam(name = "place", required = false) Integer place
            , @RequestParam(name = "category", required = false) TicketCategory category
            , @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {

        TicketFilter.Builder builder = new TicketFilter.Builder()
                .title(title)
                .place(place)
                .ticketCategory(category)
                .date(date);

        return ticketService.getBookedTickets(userId, builder.build());
    }

    @PutMapping("/book/{userId}")
    public List<BookedTicket> bookTicket(@PathVariable("userId") String userId, @RequestBody List<String> ticketIds) {
        return ticketService.bookTickets(userId, ticketIds);
    }
}
