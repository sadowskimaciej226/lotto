package pl.joble.domain.resultchecker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WinnerRepository {
    List<Player> saveAll(List<Player> winnersToSave);
    Optional<Player> findById(String id);


}
