package pl.joble.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class WinningNumbersRepositoryTestImpl implements WinningNumbersRepository {
    Map<String, WinningNumbers> db = new ConcurrentHashMap<>();
    Integer id =0;

    @Override
    public WinningNumbers save(WinningNumbers winningNumbers) {
       Integer currentId = id+1;
       db.put(currentId.toString(), winningNumbers);
       return winningNumbers;
    }

    @Override
    public Optional<WinningNumbers> findByDate(LocalDateTime date) {
        return db.values().stream()
                .filter(winningNumbers -> winningNumbers.drawDate().equals(date))
                .findAny();
    }
}
