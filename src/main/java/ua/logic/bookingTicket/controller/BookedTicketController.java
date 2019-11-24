package ua.logic.bookingTicket.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.logic.bookingTicket.service.PdfGenerator;
import ua.logic.bookingTicket.TicketCategory;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.service.TicketService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/bookedTickets")
public class BookedTicketController {
    private final TicketService ticketService;
    private final PdfGenerator pdfGenerator;

    public BookedTicketController(TicketService ticketService, PdfGenerator pdfGenerator) {
        this.ticketService = ticketService;
        this.pdfGenerator = pdfGenerator;
    }

    @GetMapping
    @ResponseBody
    public Collection<BookedTicket> getBookedTickets(
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

    @GetMapping("/" + PdfGenerator.PDF_FORMAT_STRING)
    public ResponseEntity<InputStreamResource> getBookedTicketsAndReturnPdf(
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

        Collection<BookedTicket> bookedTickets = ticketService.getBookedTickets(userId, builder.build());

        return pdfGenerator.bookedTickets(bookedTickets);
    }

    @PutMapping("/book/{userId}")
    @ResponseBody
    public List<BookedTicket> bookTicket(@PathVariable("userId") String userId, @RequestBody List<String> ticketIds) {
        return ticketService.bookTickets(userId, ticketIds);
    }

    @PutMapping(value = "/book/{userId}/" + PdfGenerator.PDF_FORMAT_STRING, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> bookTicketAndReturnPdf(@PathVariable("userId") String userId, @RequestBody List<String> ticketIds) {
        List<BookedTicket> bookedTickets = ticketService.bookTickets(userId, ticketIds);

        return pdfGenerator.bookedTickets(bookedTickets);

    }
}
