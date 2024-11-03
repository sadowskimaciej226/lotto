package pl.joble.domain.resultchecker;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
record Winner(String id,
              Set<Integer> wonNumbers,
              LocalDateTime drawDate) {
}
