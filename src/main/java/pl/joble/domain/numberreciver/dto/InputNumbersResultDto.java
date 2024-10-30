package pl.joble.domain.numberreciver.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record InputNumbersResultDto(String ticketId, LocalDateTime drawDate, String message) {
}
