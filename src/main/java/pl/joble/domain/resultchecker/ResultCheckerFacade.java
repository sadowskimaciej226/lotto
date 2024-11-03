package pl.joble.domain.resultchecker;

import lombok.RequiredArgsConstructor;
import pl.joble.domain.numbergenerator.NumberGeneratorFacade;
import pl.joble.domain.numbergenerator.dto.WinningNumbersDto;
import pl.joble.domain.numberreciver.NumberReceiverFacade;
import pl.joble.domain.numberreciver.dto.TicketDto;
import pl.joble.domain.resultchecker.dto.WinnersDto;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
public class ResultCheckerFacade {
    private final NumberReceiverFacade numberReceiverFacade;
    private final NumberGeneratorFacade numberGeneratorFacade;
    private final WinnerRepository winnerRepository;

    public List<WinnersDto> saveAllWinners(){
        LocalDateTime nextDrawDate = numberReceiverFacade.getNextDrawDate();
        WinningNumbersDto winningNumbersDto = numberGeneratorFacade.generateWinningNumbers();
        List<TicketDto> winnersTickets = getWinnerTickets(nextDrawDate, winningNumbersDto);

        if(winnersTickets.isEmpty()) {
            return List.of(WinnersDto.builder()
                    .message("Unable to retrieve a winner")
                    .build());
        }

        List<Winner> winnersToSave = winnersTickets.stream()
                .map(Mapper::mapFromTicketToWinner)
                .toList();

        List<Winner> savedWinners =winnerRepository.saveAll(winnersToSave);
        return savedWinners.stream()
                .map(Mapper::mapFromEntityToDto)
                .toList();

    }

    private List<TicketDto> getWinnerTickets(LocalDateTime nextDrawDate, WinningNumbersDto winningNumbersDto) {
        return numberReceiverFacade.userNumbers(nextDrawDate).stream()
                .filter(ticketDto -> ticketDto.numbersFromUser().containsAll(winningNumbersDto.winningNumbers()))
                .toList();
    }

    public List<WinnersDto> getWinnersByDate(LocalDateTime drawDate) {
        List<Winner> winners = winnerRepository.findAllByDate(drawDate);
        return winners.stream()
                .map(Mapper::mapFromEntityToDto)
                .toList();

    }
}
