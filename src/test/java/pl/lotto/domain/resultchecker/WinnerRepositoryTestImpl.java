package pl.lotto.domain.resultchecker;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.domain.resultchecker.Player;
import pl.lotto.domain.resultchecker.WinnerRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
    public boolean existsById(String s) {
        return false;
    }

//    @Override
//    public List<Player> saveAll(List<Player> winnersToSave) {
//        return winnersToSave.stream()
//                .map(winner -> {
//                    Player build = Player.builder()
//                            .id(winner.id())
//                            .drawDate(winner.drawDate())
//                            .inputtedNumbers(winner.inputtedNumbers())
//                            .hitNumbers(winner.hitNumbers())
//                            .wonNumbers(winner.wonNumbers())
//                            .build();
//                    db.put(entityId.toString(), build);
//                    return build;
//                })
//                .toList();
//    }

    @Override
    public <S extends Player> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Player> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Player> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Player> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Player> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Player> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Player> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Player> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Player, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Player> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Player> List<S> saveAll(Iterable<S> entities) {
        Stream<S> stream = StreamSupport.stream(entities.spliterator(), false);
        List<S> list = stream.toList();
        list.forEach(player -> db.put(player.id(), player));
        return list;
    }

    @Override
    public List<Player> findAll() {
        return null;
    }

    @Override
    public List<Player> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Player entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Player> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Player> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Player> findAll(Pageable pageable) {
        return null;
    }
}
