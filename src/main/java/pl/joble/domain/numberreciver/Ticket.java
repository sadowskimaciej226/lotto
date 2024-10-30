package pl.joble.domain.numberreciver;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
public record Ticket(String id, Set<Integer> numbers, LocalDateTime drawDate) {
}
