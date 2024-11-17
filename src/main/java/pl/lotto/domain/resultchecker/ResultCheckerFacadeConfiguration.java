package pl.lotto.domain.resultchecker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.domain.numbergenerator.NumberGeneratorFacade;
import pl.lotto.domain.numberreciver.NumberReceiverFacade;

@Configuration
public class ResultCheckerFacadeConfiguration {
    @Bean
    public ResultCheckerFacade resultCheckerFacade(NumberReceiverFacade numberReceiverFacade,
                                             NumberGeneratorFacade numberGeneratorFacade,
                                             WinnerRepository winnerRepository){
        return new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade, winnerRepository, new WinnerRetriever());
    }

    public ResultCheckerFacade createForTest(NumberReceiverFacade numberReceiverFacade,
                                             NumberGeneratorFacade numberGeneratorFacade,
                                             WinnerRepository winnerRepository){
        return new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade, winnerRepository, new WinnerRetriever());
    }

}
