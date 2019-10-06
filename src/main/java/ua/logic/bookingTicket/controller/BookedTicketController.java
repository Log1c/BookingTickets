package ua.logic.bookingTicket.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.logic.bookingTicket.PdfGenerator;
import ua.logic.bookingTicket.TicketCategory;
import ua.logic.bookingTicket.TicketFilter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.service.TicketService;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/bookedTickets")
public class BookedTicketController {
    private final TicketService ticketService;

    public BookedTicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    @ResponseBody
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
    @ResponseBody
    public List<BookedTicket> bookTicket(@PathVariable("userId") String userId, @RequestBody List<String> ticketIds) {
        return ticketService.bookTickets(userId, ticketIds);
    }

    @PutMapping(value = "/book/{userId}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> bookTicketAndReturnPdf(@PathVariable("userId") String userId, @RequestBody List<String> ticketIds) {
        List<BookedTicket> bookedTickets = ticketService.bookTickets(userId, ticketIds);

        //TODO for test only
        BookedTicket bookedTicket = bookedTickets.get(0);
        bookedTicket.getId();

        Optional<Ticket> ticket = ticketService.getTicket(bookedTicket.getId());

        ByteArrayInputStream ticketStream = PdfGenerator.report(ticket.get());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ticket.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(ticketStream));
    }

//    @PutMapping(value = "/book/{userId}/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<List<InputStreamResource>> bookTicketAndReturnPdf(@PathVariable("userId") String userId, @RequestBody List<String> ticketIds) {
//        List<BookedTicket> bookedTickets = ticketService.bookTickets(userId, ticketIds);
//        List<InputStreamResource> result = bookedTickets.stream()
//                .map(b -> ticketService.getTicket(b.getId()))
//                .filter(Optional::isPresent)
//                .map(t -> PdfGenerator.report(t.get()))
//                .map(byteArrayInputStream -> new InputStreamResource(byteArrayInputStream))
//                .collect(Collectors.toList());
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=ticket.pdf");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(result);
//    }
}
