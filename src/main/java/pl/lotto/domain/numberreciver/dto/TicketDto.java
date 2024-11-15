package pl.lotto.domain.numberreciver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record TicketDto(String ticketId, LocalDateTime drawDate, Set<Integer> numbersFromUser) {
}
