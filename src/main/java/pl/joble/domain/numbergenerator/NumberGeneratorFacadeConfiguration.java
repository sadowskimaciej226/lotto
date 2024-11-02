package pl.joble.domain.numbergenerator;

import pl.joble.domain.numberreciver.NumberReceiverFacade;

import java.time.Clock;

public class NumberGeneratorFacadeConfiguration {

    public NumberGeneratorFacade createNumberGeneratorFacade(
                                                             NumberReceiverFacade numberReceiverFacade,
                                                             WinningNumbersRepository winningNumbersRepository){

        return new NumberGeneratorFacade(new RandomNumberGenerator(), numberReceiverFacade, winningNumbersRepository);
    }
}
