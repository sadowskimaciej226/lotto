package pl.lotto.infrastructure.numberannouncer.controller.error;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.lotto.domain.resultchecker.TicketNotFoundException;

@ControllerAdvice
@Log4j2
public class ResultAnnouncerErrorHandler {

    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultAnnouncerErrorResponse handleTicketNotFoundException(TicketNotFoundException exception){
        String message = exception.getMessage();
        log.error(message);
        return new ResultAnnouncerErrorResponse(message, HttpStatus.NOT_FOUND);
    }
}
