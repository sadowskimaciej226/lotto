package pl.joble.domain.numberreciver;

import pl.joble.domain.numberreciver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;

public interface NumberReceiverRepository {
    Ticket save(Ticket ticket);

    List<Ticket> findAllByDate(LocalDateTime drawDate);

}
