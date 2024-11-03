package pl.joble.domain.resultchecker.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record WinnersDto(String id,
                         Set<Integer> wonNumbers,
                         LocalDateTime drawDate,
                         String message) {
}
