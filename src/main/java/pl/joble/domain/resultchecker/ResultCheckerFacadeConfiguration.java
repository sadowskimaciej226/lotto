package pl.joble.domain.resultchecker;

import pl.joble.domain.numbergenerator.NumberGeneratorFacade;
import pl.joble.domain.numberreciver.NumberReceiverFacade;

public class ResultCheckerFacadeConfiguration {

    public ResultCheckerFacade createForTest(NumberReceiverFacade numberReceiverFacade,
                                             NumberGeneratorFacade numberGeneratorFacade,
                                             WinnerRepository winnerRepository){
        return new ResultCheckerFacade(numberReceiverFacade, numberGeneratorFacade, winnerRepository, new WinnerRetriever());
    }

}
