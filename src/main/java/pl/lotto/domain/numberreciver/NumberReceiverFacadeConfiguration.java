package pl.lotto.domain.numberreciver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;


@Configuration
public class NumberReceiverFacadeConfiguration {


    @Bean
    public Clock clock(){
        return Clock.systemUTC();
    }
    @Bean
    public NumberReceiverFacade numberReceiverFacade(NumberReceiverRepository repository, Clock clock){
        return new NumberReceiverFacade(new Validator(), repository, clock, new DrawDateGenerator());
    }
    public NumberReceiverFacade createNumberReceiverFacadeForTest(NumberReceiverRepository repository, Clock clock){
        return new NumberReceiverFacade(new Validator(), repository, clock, new DrawDateGenerator());
    }
}
