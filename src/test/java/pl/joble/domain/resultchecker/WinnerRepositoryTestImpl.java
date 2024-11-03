package pl.joble.domain.resultchecker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class WinnerRepositoryTestImpl implements WinnerRepository {
    Map<String, Winner> db = new ConcurrentHashMap<>();
    Integer entityId = 0;

    @Override
    public List<Winner> findAllByDate(LocalDateTime drawDate) {
        return db.values()
                .stream()
                .filter(winner -> winner.drawDate().equals(drawDate))
                .toList();
    }

    @Override
    public List<Winner> saveAll(List<Winner> winnersToSave) {
        return winnersToSave.stream()
                .map(winner -> {
                    entityId++;
                    Winner build = Winner.builder()
                            .id(entityId.toString())
                            .drawDate(winner.drawDate())
                            .wonNumbers(winner.wonNumbers())
                            .build();
                    db.put(entityId.toString(), build);
                    return build;
                })
                .toList();
    }
}
