package pl.lotto.domain.numberreciver.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record InputNumbersResultDto(String ticketId, LocalDateTime drawDate, Set<Integer> inputNumbers, String message) {
}
