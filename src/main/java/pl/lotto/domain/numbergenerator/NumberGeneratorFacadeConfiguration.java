package pl.lotto.domain.numbergenerator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.domain.numberreciver.NumberReceiverFacade;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration
public class NumberGeneratorFacadeConfiguration {


    @Bean
    public NumberGeneratorFacade numberGeneratorFacade(
            RandomNumberGenerable generable,
            NumberReceiverFacade numberReceiverFacade,
            WinningNumbersRepository winningNumbersRepository,
            WinningNumbersProperties properties){

        return new NumberGeneratorFacade(generable, numberReceiverFacade, winningNumbersRepository, properties);
    }

    public NumberGeneratorFacade createNumberGeneratorFacadeForTest(
                                                             NumberReceiverFacade numberReceiverFacade,
                                                             WinningNumbersRepository winningNumbersRepository){
        WinningNumbersProperties properties = WinningNumbersProperties.builder()
                .count(6)
                .upperBand(99)
                .lowerBand(1)
                .build();

        return numberGeneratorFacade(new RandomNumberGenerator(), numberReceiverFacade, winningNumbersRepository, properties);
    }
}
