package pl.lotto.domain.numbergenerator;

import pl.lotto.domain.numberreciver.NumberReceiverFacade;

public class NumberGeneratorFacadeConfiguration {

    public NumberGeneratorFacade createNumberGeneratorFacadeForTest(
                                                             NumberReceiverFacade numberReceiverFacade,
                                                             WinningNumbersRepository winningNumbersRepository){

        return new NumberGeneratorFacade(new RandomNumberGenerator(), numberReceiverFacade, winningNumbersRepository);
    }
}
