package pl.lotto.domain.resultchecker;

import pl.lotto.domain.resultchecker.Player;
import pl.lotto.domain.resultchecker.WinnerRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class WinnerRepositoryTestImpl implements WinnerRepository {
    Map<String, Player> db = new ConcurrentHashMap<>();
    Integer entityId = 0;

    @Override
    public Optional<Player> findById(String id) {
        return db.values()
                .stream()
                .filter(winner -> winner.id().equals(id))
                .findAny();
    }

    @Override
    public List<Player> saveAll(List<Player> winnersToSave) {
        return winnersToSave.stream()
                .map(winner -> {
                    Player build = Player.builder()
                            .id(winner.id())
                            .drawDate(winner.drawDate())
                            .inputtedNumbers(winner.inputtedNumbers())
                            .hitNumbers(winner.hitNumbers())
                            .wonNumbers(winner.wonNumbers())
                            .build();
                    db.put(entityId.toString(), build);
                    return build;
                })
                .toList();
    }
}
