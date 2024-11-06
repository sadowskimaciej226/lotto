package pl.lotto.domain.resultchecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import pl.lotto.domain.numbergenerator.NumberGeneratorFacade;
import pl.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.domain.numberreciver.NumberReceiverFacade;
import pl.lotto.domain.numberreciver.dto.TicketDto;
import pl.lotto.domain.resultchecker.dto.PlayerDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ResultCheckerFacadeTest {
    ResultCheckerFacadeConfiguration config = new ResultCheckerFacadeConfiguration();
    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
    NumberGeneratorFacade numberGeneratorFacade = mock(NumberGeneratorFacade.class);
    ResultCheckerFacade facade = config.createForTest(
            numberReceiverFacade,
            numberGeneratorFacade,
            new WinnerRepositoryTestImpl());
    @BeforeEach
    public void setUp(){
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2024,11,2,8,00,00);
        //when
        when(numberReceiverFacade.getNextDrawDate()).thenReturn(localDateTime);
        when(numberReceiverFacade.userNumbers(localDateTime)).thenReturn(List.of(TicketDto.builder()
                .ticketId("1")
                .numbersFromUser(Set.of(1,2,3,4,5,6))
                .drawDate(localDateTime)
                .build()));
        when(numberGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1,2,3,4,5,6))
                .drawDate(localDateTime)
                .build());
    }
    @Test
    public void should_returns_player_with_his_and_lottery_numbers(){


        //given
        //when
        List<PlayerDto> playerDtos = facade.saveAllPlayers();
        //then
        assertThat(playerDtos.get(0).wonNumbers(), equalTo(playerDtos.get(0).inputtedNumbers()));
    }
    @Test
    public void should_returns_number_of_matched_numbers(){

        //given
        //when
        List<PlayerDto> playerDtos = facade.saveAllPlayers();
        //then
        assertThat(playerDtos.get(0).hitNumbers(), equalTo(6));
    }
    @Test
    public void should_returns_empty_list_when_there_are_no_players(){

        //given
        //when
        when(numberReceiverFacade.userNumbers(ArgumentMatchers.any())).thenReturn(List.of());
        List<PlayerDto> playerDtos = facade.saveAllPlayers();
        //then
        assertThat(playerDtos, is(empty()));
    }
    @Test
    public void should_returns_player_by_ticket_id() {
        //given
        //when
        facade.saveAllPlayers();

        PlayerDto byId = facade.findById("1");
        //then
        assertThat(byId.hitNumbers(), equalTo(6));
        assertThat(byId.drawDate(), equalTo(LocalDateTime.of(2024,11,2,8,00,00)));
    }
    @Test
    public void should_throws_exception_if_player_dose_not_exist(){
        //given
        //when
        //then
        assertThrows(TicketNotFoundException.class, () -> facade.findById("id"));
    }



}