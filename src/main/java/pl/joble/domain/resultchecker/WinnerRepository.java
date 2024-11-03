package pl.joble.domain.resultchecker;

import java.time.LocalDateTime;
import java.util.List;

public interface WinnerRepository {
    List<Winner> saveAll(List<Winner> winnersToSave);

    List<Winner> findAllByDate(LocalDateTime drawDate);
}
