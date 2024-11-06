package pl.lotto.domain.resultchecker.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record PlayerDto(String id,
                        Set<Integer> wonNumbers,
                        Set<Integer> inputtedNumbers,
                        Integer hitNumbers,
                        LocalDateTime drawDate) {
}
