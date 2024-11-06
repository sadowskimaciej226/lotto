package pl.lotto.domain.resultchecker;

import pl.lotto.domain.numbergenerator.NumberGeneratorFacade;
import pl.lotto.domain.numberreciver.NumberReceiverFacade;

public class ResultCheckerFacadeConfiguration {

    public ResultCheckerFacade createForTest(NumberReceiverFacade numberReceiverFacade,
                                             NumberGeneratorFacade numberGeneratorFacade,
                                             WinnerRepository winnerRepository){
        return new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade, winnerRepository, new WinnerRetriever());
    }

}
