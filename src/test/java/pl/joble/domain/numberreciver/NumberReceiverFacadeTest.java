package pl.joble.domain.numberreciver;


import org.junit.jupiter.api.Test;
import pl.joble.domain.AdjustableClock;
import pl.joble.domain.numberreciver.dto.InputNumbersResultDto;
import pl.joble.domain.numberreciver.dto.TicketDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

class NumberReceiverFacadeTest {
    NumberReceiverFacadeConfiguration config = new NumberReceiverFacadeConfiguration();
    Clock clock = new AdjustableClock(LocalDateTime.of(2024,10,30,10,00,00).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
    NumberReceiverFacade facade = config.createNumberReceiverFacade(new NumberReceiverRepositoryTestImpl(), clock);

    @Test
    public void should_return_success_when_user_give_6_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 2, 3, 4, 5, 6);
        //when
        InputNumbersResultDto result = facade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message(), equalTo("Success"));
    }
    @Test
    public void should_return_fail_when_user_not_give_6_numbers(){
        //given
        Set<Integer> numbersFromUser = Set.of(1, 3, 4, 5, 6);
        //when
        InputNumbersResultDto result = facade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message(), equalTo("Failed"));
    }
    @Test
    public void should_return_fail_if_numbers_are_out_of_range(){
        //given
        Set<Integer> numbersFromUser = Set.of(1,2, 3, 400, 5, 6);
        //when
        InputNumbersResultDto result = facade.inputNumbers(numbersFromUser);
        //then
        assertThat(result.message(), equalTo("Failed"));
    }
    @Test
    public void should_return_saved_tickets_by_date(){
        //given
        Set<Integer> numbersFromUser = Set.of(1,2, 3, 4, 5, 6);
        InputNumbersResultDto result = facade.inputNumbers(numbersFromUser);
        //when
        List<TicketDto> ticketDtos = facade.userNumbers(LocalDateTime.of(2024,10,30,11,00,00));
        //then
        assertThat(ticketDtos, contains(TicketDto.builder()
                .ticketId(result.ticketId())
                .drawDate(result.drawDate())
                .numbersFromUser(numbersFromUser)
                .build()));
    }

}