package ua.logic.bookingTicket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PdfGeneratorUnattainableAdvice {
    @ResponseBody
    @ExceptionHandler(PdfGeneratorUnattainableException.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @SuppressWarnings("unused")
    String pdfGeneratorUnattainableHandler(PdfGeneratorUnattainableException ex) {
        return ex.getMessage();
    }
}
