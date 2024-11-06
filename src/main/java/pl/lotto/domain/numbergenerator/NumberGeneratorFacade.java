package pl.lotto.domain.numbergenerator;

import lombok.RequiredArgsConstructor;
import pl.lotto.domain.numbergenerator.dto.WinningNumbersDto;
import pl.lotto.domain.numberreciver.NumberReceiverFacade;

import java.time.LocalDateTime;

//ten moduł
//zapisuje liczby wygrywające do bazy danych wraz z data losowania

@RequiredArgsConstructor
public class NumberGeneratorFacade {
    private final RandomNumberGenerable generator;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumbersRepository repository;


    public WinningNumbersDto generateWinningNumbers(){
        WinningNumbers winningNumbersToSave = WinningNumbers.builder()
                .winningNumbers(generator.generate6RandomNumbers().randomNumbers())
                .drawDate(numberReceiverFacade.getNextDrawDate())
                .build();

        WinningNumbers savedNumbers = repository.save(winningNumbersToSave);

        return WinningNumbersDto.builder()
                .winningNumbers(savedNumbers.winningNumbers())
                .drawDate(savedNumbers.drawDate())
                .build();

    }

    public WinningNumbersDto findWinningNumbersByDate(LocalDateTime date){
        WinningNumbers numbersByDate = repository.findByDate(date)
                .orElseThrow(() -> new WinningNumbersNotFoundException("Not found"));
       return WinningNumbersDto.builder()
                .drawDate(numbersByDate.drawDate())
                .winningNumbers(numbersByDate.winningNumbers())
                .build();
    }
}
