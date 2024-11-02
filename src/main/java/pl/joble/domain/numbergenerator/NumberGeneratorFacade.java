package pl.joble.domain.numbergenerator;

import lombok.RequiredArgsConstructor;
import pl.joble.domain.numbergenerator.dto.WinningNumbersDto;
import pl.joble.domain.numberreciver.NumberReceiverFacade;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//ten moduł
//zapisuje liczby wygrywające do bazy danych wraz z data losowania

@RequiredArgsConstructor
public class NumberGeneratorFacade {
    private final RandomNumberGenerator generator;
    private final NumberReceiverFacade numberReceiverFacade;
    private final WinningNumbersRepository repository;


    public WinningNumbersDto generateWinningNumbers(){
        WinningNumbers winningNumbersToSave = WinningNumbers.builder()
                .winningNumbers(generator.generate6RandomNumbers())
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
