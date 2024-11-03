package pl.joble.domain.resultchecker;

import org.junit.jupiter.api.Test;
import pl.joble.domain.numbergenerator.NumberGeneratorFacade;
import pl.joble.domain.numbergenerator.dto.WinningNumbersDto;
import pl.joble.domain.numberreciver.NumberReceiverFacade;
import pl.joble.domain.numberreciver.dto.TicketDto;
import pl.joble.domain.resultchecker.dto.WinnersDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
    @Test
    public void should_return_success_when_retrieved_winner(){
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2024, 11, 2, 10, 0, 0);
        //when
        when(numberReceiverFacade.getNextDrawDate()).thenReturn(localDateTime);
        when(numberReceiverFacade.userNumbers(localDateTime)).thenReturn(List.of(TicketDto.builder()
                        .numbersFromUser(Set.of(1,2,3,4,5,6))
                        .drawDate(localDateTime)
                        .ticketId("1")
                .build()));
        when(numberGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1,2,3,4,5,6))
                .drawDate(localDateTime)
                .build());
        List<WinnersDto> winnersDtos = facade.saveAllWinners();

        //then
        assertThat(winnersDtos.getFirst().message(), equalTo("Winners success to retrieve"));
        assertThat(winnersDtos, hasSize(1));

    }
    @Test
    public void should_return_fail_when_retrieved_winner(){
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2024, 11, 2, 10, 0, 0);
        //when
        when(numberReceiverFacade.getNextDrawDate()).thenReturn(localDateTime);
        when(numberReceiverFacade.userNumbers(localDateTime)).thenReturn(List.of(TicketDto.builder()
                .numbersFromUser(Set.of(10,20,30,40,50,60))
                .drawDate(localDateTime)
                .ticketId("1")
                .build()));
        when(numberGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1,2,3,4,5,6))
                .drawDate(localDateTime)
                .build());
        List<WinnersDto> winnersDtos = facade.saveAllWinners();

        //then
        assertThat(winnersDtos.getFirst().message(), equalTo("Unable to retrieve a winner"));
        assertThat(winnersDtos, hasSize(1));

    }

    @Test
    public void should_save_all_winners_to_database(){
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2024, 11, 2, 10, 0, 0);
        //when
        when(numberReceiverFacade.getNextDrawDate()).thenReturn(localDateTime);
        when(numberReceiverFacade.userNumbers(localDateTime)).thenReturn(List.of(TicketDto.builder()
                .numbersFromUser(Set.of(1,2,3,4,5,6))
                .drawDate(localDateTime)
                .ticketId("1")
                .build(),
          TicketDto.builder()
                 .numbersFromUser(Set.of(1,2,3,4,5,6))
                 .drawDate(localDateTime)
                 .ticketId("2")
                 .build()));
        when(numberGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1,2,3,4,5,6))
                .drawDate(localDateTime)
                .build());
        List<WinnersDto> winnersDtos = facade.saveAllWinners();

        //then
        assertThat(winnersDtos.getFirst().message(), equalTo("Winners success to retrieve"));
        assertThat(winnersDtos, hasSize(2));

    }

    @Test
    public void should_returns_all_winners_by_date(){
        //given
        LocalDateTime localDateTime = LocalDateTime.of(2024, 11, 2, 10, 0, 0);
        //when
        when(numberReceiverFacade.getNextDrawDate()).thenReturn(localDateTime);
        when(numberGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1,2,3,4,5,6))
                .drawDate(localDateTime)
                .build());
        when(numberReceiverFacade.userNumbers(localDateTime)).thenReturn(List.of(TicketDto.builder()
                        .numbersFromUser(Set.of(1,2,3,4,5,6))
                        .drawDate(localDateTime)
                        .ticketId("1")
                        .build(),
                TicketDto.builder()
                        .numbersFromUser(Set.of(1,2,3,4,5,6))
                        .drawDate(localDateTime)
                        .ticketId("2")
                        .build()));

        facade.saveAllWinners();

        List<WinnersDto> winnersDtos = facade.getWinnersByDate(localDateTime);

        //then
        assertThat(winnersDtos, hasSize(2));
        assertThat(winnersDtos.getFirst().drawDate(), equalTo(localDateTime));
    }

    @Test
    public void should_returns_empty_list_if_there_are_no_winners_for_given_date(){
//given
        LocalDateTime localDateTime = LocalDateTime.of(2024, 11, 2, 10, 0, 0);
        //when
        when(numberReceiverFacade.getNextDrawDate()).thenReturn(localDateTime);
        when(numberGeneratorFacade.generateWinningNumbers()).thenReturn(WinningNumbersDto.builder()
                .winningNumbers(Set.of(1,2,3,4,5,6))
                .drawDate(localDateTime)
                .build());

        facade.saveAllWinners();

        List<WinnersDto> winnersDtos = facade.getWinnersByDate(localDateTime);

        //then
        assertThat(winnersDtos, hasSize(0));

    }



}