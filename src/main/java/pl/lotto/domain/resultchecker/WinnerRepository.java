package pl.lotto.domain.resultchecker;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface WinnerRepository extends MongoRepository<Player, String> {

}
