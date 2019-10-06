package ua.logic.bookingTicket;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import ua.logic.bookingTicket.entity.Ticket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PdfGenerator {
    public static ByteArrayInputStream report(Ticket ticket) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(2);

            table.addCell(new PdfPCell(new Phrase("id")));
            table.addCell(new PdfPCell(new Phrase(ticket.getId())));

            table.addCell(new PdfPCell(new Phrase("title")));
            table.addCell(new PdfPCell(new Phrase(ticket.getTitle())));

            table.addCell(new PdfPCell(new Phrase("date")));
            table.addCell(new PdfPCell(new Phrase(ticket.getDate().toString())));

            table.addCell(new PdfPCell(new Phrase("category")));
            table.addCell(new PdfPCell(new Phrase(ticket.getCategory().toString())));

            table.addCell(new PdfPCell(new Phrase("category")));
            table.addCell(new PdfPCell(new Phrase(ticket.getPlace().toString())));

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);

            document.close();
        } catch (DocumentException ex) {
//            logger.error("Error occurred: {0}", ex);//TODO handle it
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}