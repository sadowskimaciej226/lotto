package pl.lotto.domain.numberreciver;

import java.time.LocalDateTime;
import java.util.List;

public interface NumberReceiverRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAllByDate(LocalDateTime drawDate);

}
