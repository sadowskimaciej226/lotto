package pl.lotto.domain.resultannouncer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import pl.lotto.domain.resultannouncer.dto.AnnouncerResponseDto;
import pl.lotto.domain.resultchecker.ResultCheckerFacade;
import pl.lotto.domain.resultchecker.dto.PlayerDto;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultAnnouncerFacadeTest {

    ResultAnnouncerFacadeConfiguration config = new ResultAnnouncerFacadeConfiguration();
    ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);
    ResultAnnouncerFacade facade;

    @BeforeEach
    public void setUp(){
        Map<String, AnnouncerResponse> db = new ConcurrentHashMap<>();
        facade = config.createForTest(resultCheckerFacade,
                new ResultAnnouncerRepository() {
                    @Override
                    public <S extends AnnouncerResponse> S insert(S entity) {
                        return null;
                    }

                    @Override
                    public <S extends AnnouncerResponse> List<S> insert(Iterable<S> entities) {
                        return null;
                    }

                    @Override
                    public <S extends AnnouncerResponse> List<S> findAll(Example<S> example) {
                        return null;
                    }

                    @Override
                    public <S extends AnnouncerResponse> List<S> findAll(Example<S> example, Sort sort) {
                        return null;
                    }

                    @Override
                    public <S extends AnnouncerResponse> List<S> saveAll(Iterable<S> entities) {
                        return null;
                    }

                    @Override
                    public List<AnnouncerResponse> findAll() {
                        return null;
                    }

                    @Override
                    public List<AnnouncerResponse> findAllById(Iterable<String> strings) {
                        return null;
                    }

                    @Override
                    public <S extends AnnouncerResponse> S save(S entity) {
                        db.put(entity.ticketId(), entity);
                        return entity;
                    }

                    @Override
                    public Optional<AnnouncerResponse> findById(String s) {
                        return Optional.empty();
                    }

                    @Override
                    public boolean existsById(String s) {
                        if(db.get(s)==null) return false;
                        else return true;
                    }

                    @Override
                    public long count() {
                        return 0;
                    }

                    @Override
                    public void deleteById(String s) {

                    }

                    @Override
                    public void delete(AnnouncerResponse entity) {

                    }

                    @Override
                    public void deleteAllById(Iterable<? extends String> strings) {

                    }

                    @Override
                    public void deleteAll(Iterable<? extends AnnouncerResponse> entities) {

                    }

                    @Override
                    public void deleteAll() {

                    }

                    @Override
                    public List<AnnouncerResponse> findAll(Sort sort) {
                        return null;
                    }

                    @Override
                    public Page<AnnouncerResponse> findAll(Pageable pageable) {
                        return null;
                    }

                    @Override
                    public <S extends AnnouncerResponse> Optional<S> findOne(Example<S> example) {
                        return Optional.empty();
                    }

                    @Override
                    public <S extends AnnouncerResponse> Page<S> findAll(Example<S> example, Pageable pageable) {
                        return null;
                    }

                    @Override
                    public <S extends AnnouncerResponse> long count(Example<S> example) {
                        return 0;
                    }

                    @Override
                    public <S extends AnnouncerResponse> boolean exists(Example<S> example) {
                        return false;
                    }

                    @Override
                    public <S extends AnnouncerResponse, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                        return null;
                    }
                });
        when(resultCheckerFacade.findById("1")).thenReturn(PlayerDto.builder()
                        .id("1")
                .build());
    }

    @Test
    public void should_returns_player_dto_once(){
        //given
        //when
        AnnouncerResponseDto playerByTicketId = facade.findPlayerByTicketId("1");
        //then
        assertThat(playerByTicketId, not(equalTo(null)));
    }
    @Test
    public void should_throws_exception_if_it_is_asked_more_than_once(){
        //given
        //when
        AnnouncerResponseDto playerByTicketId = facade.findPlayerByTicketId("1");
        //then
        assertThrows(PlayerAlreadyCheckedException.class, () -> facade.findPlayerByTicketId("1"));
    }

}