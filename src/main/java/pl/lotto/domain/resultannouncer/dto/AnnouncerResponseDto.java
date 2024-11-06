package pl.lotto.domain.resultannouncer.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record AnnouncerResponseDto(String ticketId,
                                   Set<Integer> inputtedNumbers,
                                   Set<Integer> wonNumbers,
                                   Integer hitNumbers,
                                   LocalDateTime drawDate) {
}
