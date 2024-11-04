package pl.joble.domain.resultannouncer;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
record AnnouncerResponse(String ticketId,
                         Set<Integer> inputtedNumbers,
                         Set<Integer> wonNumbers,
                         Integer hitNumbers,
                         LocalDateTime drawDate) {
}
