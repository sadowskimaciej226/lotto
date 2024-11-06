package pl.lotto.domain.numberreciver;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
record Ticket(String id, Set<Integer> numbers, LocalDateTime drawDate) {
}
