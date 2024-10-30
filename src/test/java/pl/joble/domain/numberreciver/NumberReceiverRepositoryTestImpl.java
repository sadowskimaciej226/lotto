package pl.joble.domain.numberreciver;

import pl.joble.domain.numberreciver.dto.TicketDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class NumberReceiverRepositoryTestImpl implements NumberReceiverRepository {

    Map<String, Ticket> db = new ConcurrentHashMap<>();
    @Override
    public Ticket save(Ticket ticket) {
        db.put(ticket.id(), ticket);
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
