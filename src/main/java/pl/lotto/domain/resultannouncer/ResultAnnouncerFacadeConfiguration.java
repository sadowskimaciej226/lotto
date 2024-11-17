package pl.lotto.domain.resultannouncer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.lotto.domain.resultchecker.ResultCheckerFacade;

@Configuration
public class ResultAnnouncerFacadeConfiguration {

    @Bean
    public ResultAnnouncerFacade resultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade, ResultAnnouncerRepository repository){
        return new  ResultAnnouncerFacade(resultCheckerFacade, repository);
    }


    public ResultAnnouncerFacade createForTest(ResultCheckerFacade resultCheckerFacade, ResultAnnouncerRepository repository){
        return new  ResultAnnouncerFacade(resultCheckerFacade, repository);
    }


}
