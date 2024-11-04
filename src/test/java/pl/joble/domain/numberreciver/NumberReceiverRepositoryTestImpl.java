package pl.joble.domain.numberreciver;

import pl.joble.domain.numberreciver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class NumberReceiverRepositoryTestImpl implements NumberReceiverRepository {

    Map<String, Ticket> db = new ConcurrentHashMap<>();
    Integer entityId = 0;
    @Override
    public Ticket save(Ticket ticket) {
        Integer currentId = entityId+1;
        db.put(currentId.toString(), ticket);
        return ticket;
    }

    @Override
    public List<Ticket> findAllByDate(LocalDateTime drawDate) {
        return db.values()
                .stream()
                .filter(ticket -> ticket.drawDate().equals(drawDate))
                .toList();
    }

}
