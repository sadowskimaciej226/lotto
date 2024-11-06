package pl.lotto.domain.numbergenerator;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.domain.numberreciver.NumberReceiverFacade;

import java.time.*;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberGeneratorFacadeTest {
    NumberGeneratorFacadeConfiguration config = new NumberGeneratorFacadeConfiguration();
    @Mock
    NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
    NumberGeneratorFacade facade = config.createNumberGeneratorFacade(numberReceiverFacade, new WinningNumbersRepositoryTestImpl());

    @Test
    public void should_returns_6_unique_winning_numbers(){
        //given
        //when
        Set<Integer> winningNumbers = facade.generateWinningNumbers().winningNumbers();
        //then
        assertThat(winningNumbers, hasSize(6));
    }
    @Test
    public void should_generate_6_numbers_in_range_1_to_99(){
        //given
        //when
        Set<Integer> winningNumbers = facade.generateWinningNumbers().winningNumbers();
        //then
        assertThat(winningNumbers, everyItem(allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(99))));
    }
    @Test
    public void should_return_saved_numbers_with_draw_date(){
        //given
        //when
        when(numberReceiverFacade.getNextDrawDate()).thenReturn(LocalDateTime.of(2024,11,2,8,0,0));

        WinningNumbersDto winningNumbersDto = facade.generateWinningNumbers();
        //then
        assertThat(winningNumbersDto.drawDate().getDayOfWeek(), equalTo(DayOfWeek.SATURDAY));
    }
    @Test
    public void should_return_winning_numbers_by_date(){
        //given
        when(numberReceiverFacade.getNextDrawDate()).thenReturn(LocalDateTime.of(2024,11,2,10,0,0));
        facade.generateWinningNumbers();
        //when
        WinningNumbersDto winningNumbersByDate = facade.findWinningNumbersByDate(LocalDateTime.of(2024, 11, 2, 10, 0));
        //then
        assertThat(winningNumbersByDate.drawDate(), equalTo(LocalDateTime.of(2024, 11, 2, 10, 0)));
        assertThat(winningNumbersByDate.drawDate().getDayOfWeek(), equalTo(DayOfWeek.SATURDAY));

    }
    @Test
    public void should_throw_exception_when_there_is_no_winning_numbers_by_date(){
        //given
        when(numberReceiverFacade.getNextDrawDate()).thenReturn(LocalDateTime.of(2024,11,20,10,0,0));
        facade.generateWinningNumbers();
        //when
        //then
        assertThrows(WinningNumbersNotFoundException.class, () -> facade.findWinningNumbersByDate(LocalDateTime.of(2024, 11, 2, 10, 0)));
    }
}