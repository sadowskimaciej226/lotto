package pl.lotto.domain.numberreciver;

import lombok.RequiredArgsConstructor;
import pl.lotto.domain.numberreciver.dto.InputNumbersResultDto;
import pl.lotto.domain.numberreciver.dto.TicketDto;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
public class NumberReceiverFacade {
     private final Validator validator;
     private final NumberReceiverRepository repository;
     private final Clock clock;
     private final DrawDateGenerator dateGenerator;


     public InputNumbersResultDto inputNumbers(Set<Integer> numbersFromUser){
          if (validator.areNumbersCorrect(numbersFromUser)) {
               String id = UUID.randomUUID().toString();
               LocalDateTime drawDate = dateGenerator.generateNextDrawDate(clock);
               Ticket savedTicket = repository.save(Ticket.builder()
                               .numbers(numbersFromUser)
                               .drawDate(drawDate)
                       .build());
               return  InputNumbersResultDto.builder()
                       .drawDate(savedTicket.drawDate())
                       .ticketId(savedTicket.id())
                       .message("Success")
                       .build();
          }
          else return InputNumbersResultDto.builder()
                  .message("Failed")
                  .build();
     }
     public List<TicketDto> userNumbers(LocalDateTime date){
          List<Ticket> allTickets = repository.findAllByDate(date);
          return allTickets.stream()
                  .map(TicketMapper::mapToDto)
                  .toList();
     }
     public LocalDateTime getNextDrawDate(){
          return dateGenerator.generateNextDrawDate(clock);
     }



}
