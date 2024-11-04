package pl.joble.domain.resultannouncer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.joble.domain.resultannouncer.dto.AnnouncerResponseDto;
import pl.joble.domain.resultchecker.ResultCheckerFacade;
import pl.joble.domain.resultchecker.dto.PlayerDto;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultAnnouncerFacadeTest {

    ResultAnnouncerFacadeConfiguration config = new ResultAnnouncerFacadeConfiguration();
    ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);
    ResultAnnouncerFacade facade = config.createForTest(resultCheckerFacade,
            new ResultAnnouncerRepositoryImpl());
    @BeforeEach
    public void setUp(){
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