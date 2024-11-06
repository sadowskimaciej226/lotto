package pl.lotto.domain.resultchecker;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
record Player(String id,
              Set<Integer> wonNumbers,
              Set<Integer> inputtedNumbers,
              Integer hitNumbers,
              LocalDateTime drawDate) {
}
