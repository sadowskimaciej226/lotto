package pl.joble.domain.numberreciver;

import java.time.Clock;

public class NumberReceiverFacadeConfiguration {
    public NumberReceiverFacade createNumberReceiverFacade(NumberReceiverRepository repository, Clock clock){
        return new NumberReceiverFacade(new Validator(), repository, clock);
    }
}
