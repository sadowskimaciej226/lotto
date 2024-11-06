package pl.lotto.domain.resultchecker;

import java.util.List;
import java.util.Optional;

public interface WinnerRepository {
    List<Player> saveAll(List<Player> winnersToSave);
    Optional<Player> findById(String id);


}
