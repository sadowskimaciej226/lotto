package pl.lotto.domain.resultchecker;

import pl.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.domain.numberreciver.dto.TicketDto;
import pl.lotto.domain.resultchecker.dto.PlayerDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class WinnerRetriever {
    public List<PlayerDto> retrieveWinner(WinningNumbersDto winningNumbersDto,
                                          List<TicketDto> tickets) {
        List<PlayerDto> list = tickets.stream()
                .map(ticketDto -> {
                    return PlayerDto.builder()
                            .id(ticketDto.ticketId())
                            .inputtedNumbers(ticketDto.numbersFromUser())
                            .hitNumbers(calculateHits(winningNumbersDto.winningNumbers(), ticketDto.numbersFromUser()))
                            .wonNumbers(winningNumbersDto.winningNumbers())
                            .drawDate(winningNumbersDto.drawDate())
                            .build();
                }).toList();
        return list;

    }

    private Integer calculateHits(Set<Integer> winningNumbers, Set<Integer> numberFromUser) {
        return winningNumbers.stream()
                .filter(numberFromUser::contains)
                .collect(Collectors.toSet())
                .size();
    }
}
