package pl.joble.domain.numberreciver;


import org.junit.jupiter.api.Test;
import pl.joble.domain.AdjustableClock;
import pl.joble.domain.numberreciver.dto.InputNumbersResultDto;
import pl.joble.domain.numberreciver.dto.TicketDto;

import java.time.*;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class NumberReceiverFacadeTest {
    NumberReceiverFacadeConfiguration config = new NumberReceiverFacadeConfiguration();
    AdjustableClock clock = new AdjustableClock(LocalDateTime.of(2024,11,2,8,00,00).toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
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
        List<TicketDto> ticketDtos = facade.userNumbers(LocalDateTime.of(2024,11,2,10,00,00));
        //then
        assertThat(ticketDtos, contains(TicketDto.builder()
                .ticketId(result.ticketId())
                .drawDate(result.drawDate())
                .numbersFromUser(numbersFromUser)
                .build()));
    }
    @Test
    public void should_set_the_draw_date_to_Saturday_at_10_AM(){
        //given
        Set<Integer> numbersFromUser = Set.of(1,2, 3, 4, 5, 6);
        clock.backwardInTimeBy(Duration.ofDays(9));
        facade.inputNumbers(numbersFromUser);

        //when
        List<TicketDto> ticketDtos = facade.userNumbers(LocalDateTime.of(2024,10,26,10,00,00));
        //then
        assertThat(LocalDateTime.now(clock).getDayOfWeek(), equalTo(DayOfWeek.THURSDAY));
        assertThat(ticketDtos.getFirst().drawDate().getDayOfWeek(), equalTo(DayOfWeek.SATURDAY));
    }
    @Test
    public void should_return_empty_list_if_there_aro_no_tickets_on_this_day(){
        //given
        //when
        List<TicketDto> ticketDtos = facade.userNumbers(LocalDateTime.of(1000025,11,2,10,00,00));
        //then
        assertThat(ticketDtos, is(empty()));
    }



}