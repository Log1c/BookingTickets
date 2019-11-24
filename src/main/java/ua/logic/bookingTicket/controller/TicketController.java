package ua.logic.bookingTicket.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logic.bookingTicket.service.PdfGenerator;
import ua.logic.bookingTicket.TicketCategory;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.exception.TicketNotFoundException;
import ua.logic.bookingTicket.service.TicketService;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final PdfGenerator pdfGenerator;

    public TicketController(TicketService ticketService, PdfGenerator pdfGenerator) {
        this.ticketService = ticketService;
        this.pdfGenerator = pdfGenerator;
    }

    @GetMapping
    public Collection<Ticket> getTickets() {
        return ticketService.getTickets();
    }

    @GetMapping(value = "/" + PdfGenerator.PDF_FORMAT_STRING, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getTicketsInPdf() {
        Collection<Ticket> tickets = ticketService.getTickets();

        return pdfGenerator.ticket(tickets);
    }

    @GetMapping("/{id}")
    public Ticket getTickets(@PathVariable("id") String id) {
        Optional<Ticket> ticket = ticketService.getTicket(id);
        ticket.orElseThrow(() -> new TicketNotFoundException(id));

        return ticket.get();
    }

    @GetMapping(value = "/{id}/" + PdfGenerator.PDF_FORMAT_STRING, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getTicketsInPdf(@PathVariable("id") String id) {
        Optional<Ticket> ticket = ticketService.getTicket(id);
        ticket.orElseThrow(() -> new TicketNotFoundException(id));

        return pdfGenerator.ticket(Collections.singleton(ticket.get()));
    }

    @GetMapping("/available")
    public Collection<Ticket> getAvailableTickets(
            @RequestParam(name = "title", required = false) String title
            , @RequestParam(name = "place", required = false) Integer place
            , @RequestParam(name = "category", required = false) TicketCategory category
            , @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {

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
