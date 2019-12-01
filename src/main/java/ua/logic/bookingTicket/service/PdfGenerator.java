package ua.logic.bookingTicket.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.exception.PdfGeneratorUnattainableException;
import ua.logic.bookingTicket.repository.TicketRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Optional;

@Service
public class PdfGenerator {
    public static final String PDF_FORMAT_STRING = "pdf";

    private final TicketRepository ticketRepository;

    public PdfGenerator(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public ResponseEntity<InputStreamResource> ticket(Collection<Ticket> tickets) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);
            for (Ticket ticket : tickets) {
                table.addCell(new PdfPCell(new Phrase("id")));
                table.addCell(new PdfPCell(new Phrase(ticket.getId())));

                table.addCell(new PdfPCell(new Phrase("title")));
                table.addCell(new PdfPCell(new Phrase(ticket.getTitle())));

                table.addCell(new PdfPCell(new Phrase("date")));
                table.addCell(new PdfPCell(new Phrase(ticket.getDate().toString())));

                table.addCell(new PdfPCell(new Phrase("category")));
                table.addCell(new PdfPCell(new Phrase(ticket.getCategory().toString())));

                table.addCell(new PdfPCell(new Phrase("place")));
                table.addCell(new PdfPCell(new Phrase(ticket.getPlace().toString())));
                table.addCell("");
                table.addCell("");
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
        } catch (DocumentException ex) {
            throw new PdfGeneratorUnattainableException();
        } finally {
            document.close();
        }

        return getResponse(new ByteArrayInputStream(out.toByteArray()));
    }

    public ResponseEntity<InputStreamResource> bookedTickets(Collection<BookedTicket> bookedTickets) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);
            for (BookedTicket bookedTicket : bookedTickets) {
                //TODO change it for performance
                Optional<Ticket> one = ticketRepository.findById(bookedTicket.getTicketId());
                if (!one.isPresent()) {
                    continue;
                }
                Ticket ticket = one.get();
                table.addCell(new PdfPCell(new Phrase("id")));
                table.addCell(new PdfPCell(new Phrase(ticket.getId())));

                table.addCell(new PdfPCell(new Phrase("title")));
                table.addCell(new PdfPCell(new Phrase(ticket.getTitle())));

                table.addCell(new PdfPCell(new Phrase("date")));
                table.addCell(new PdfPCell(new Phrase(ticket.getDate().toString())));

                table.addCell(new PdfPCell(new Phrase("category")));
                table.addCell(new PdfPCell(new Phrase(ticket.getCategory().toString())));

                table.addCell(new PdfPCell(new Phrase("place")));
                table.addCell(new PdfPCell(new Phrase(ticket.getPlace().toString())));

                table.addCell(new PdfPCell(new Phrase("userId")));
                table.addCell(new PdfPCell(new Phrase(bookedTicket.getUserId())));

                table.addCell("");
                table.addCell("");
            }

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
        } catch (DocumentException ex) {
            throw new PdfGeneratorUnattainableException();
        } finally {
            document.close();
        }

        return getResponse(new ByteArrayInputStream(out.toByteArray()));
    }

    private ResponseEntity<InputStreamResource> getResponse(ByteArrayInputStream stream){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ticket.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(stream));
    }

}