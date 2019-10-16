package ua.logic.bookingTicket;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ua.logic.bookingTicket.entity.BookedTicket;
import ua.logic.bookingTicket.entity.Ticket;
import ua.logic.bookingTicket.exception.PdfGeneratorUnattainableException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;

public class PdfGenerator {
    public static ByteArrayInputStream ticket(Collection<Ticket> tickets) {
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

        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream bookedTickets(Collection<BookedTicket> bookedTickets) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);
            for (BookedTicket bookedTicket : bookedTickets) {
                Ticket ticket = bookedTicket.getTicket();
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

                table.addCell(new PdfPCell(new Phrase("place")));
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

        return new ByteArrayInputStream(out.toByteArray());
    }
}