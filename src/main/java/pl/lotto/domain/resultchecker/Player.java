package pl.lotto.domain.resultchecker;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Document
record Player(@Id String id,
              Set<Integer> wonNumbers,
              Set<Integer> inputtedNumbers,
              Integer hitNumbers,
              LocalDateTime drawDate) {
}
