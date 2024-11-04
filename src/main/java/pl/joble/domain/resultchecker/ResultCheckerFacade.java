package pl.joble.domain.resultchecker;

import lombok.RequiredArgsConstructor;
import pl.joble.domain.numbergenerator.NumberGeneratorFacade;
import pl.joble.domain.numbergenerator.dto.WinningNumbersDto;
import pl.joble.domain.numberreciver.NumberReceiverFacade;
import pl.joble.domain.numberreciver.dto.TicketDto;
import pl.joble.domain.resultchecker.dto.PlayerDto;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class ResultCheckerFacade {
    private final NumberReceiverFacade numberReceiverFacade;
    private final NumberGeneratorFacade numberGeneratorFacade;
    private final WinnerRepository winnerRepository;
    private final WinnerRetriever winnerRetriever;

    public List<PlayerDto> saveAllPlayers(){
        LocalDateTime nextDrawDate = numberReceiverFacade.getNextDrawDate();
        WinningNumbersDto winningNumbersDto = numberGeneratorFacade.generateWinningNumbers();
        List<TicketDto> tickets = numberReceiverFacade.userNumbers(nextDrawDate);
        List<PlayerDto> playerDto =winnerRetriever.retrieveWinner(winningNumbersDto,tickets);
        if(playerDto.isEmpty()) {
            return List.of();
        }

        List<Player> winnersToSave = playerDto.stream()
                .map(PlayerMapper::mapFromDtoToEntity)
                .toList();

        List<Player> savedWinners =winnerRepository.saveAll(winnersToSave);
        return savedWinners.stream()
                .map(PlayerMapper::mapFromEntityToDto)
                .toList();

    }

    public PlayerDto findById(String id) {
        Player player = winnerRepository.findById(id).orElseThrow(TicketNotFoundException::new);
        return PlayerMapper.mapFromEntityToDto(player);
    }
}
