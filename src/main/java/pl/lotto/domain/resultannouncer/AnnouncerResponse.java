package pl.lotto.domain.resultannouncer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Document
record AnnouncerResponse(@Id String ticketId,
                         Set<Integer> inputtedNumbers,
                         Set<Integer> wonNumbers,
                         Integer hitNumbers,
                         LocalDateTime drawDate) {
}
