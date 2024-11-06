package pl.lotto.domain.numberreciver;

import java.time.Clock;

public class NumberReceiverFacadeConfiguration {
    public NumberReceiverFacade createNumberReceiverFacade(NumberReceiverRepository repository, Clock clock){
        return new NumberReceiverFacade(new Validator(), repository, clock, new DrawDateGenerator());
    }
}
