package pl.lotto.infrastructure.numberannouncer.controller.error;

import org.springframework.http.HttpStatus;

public record ResultAnnouncerErrorResponse(String message,
                                           HttpStatus status) {
}
