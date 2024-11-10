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
    public NumberReceiverRepository numberReceiverRepository(){
        return new NumberReceiverRepository() {
            @Override
            public Ticket save(Ticket ticket) {
                return null;
            }

            @Override
            public List<Ticket> findAllByDate(LocalDateTime drawDate) {
                return null;
            }
        };
    }

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
