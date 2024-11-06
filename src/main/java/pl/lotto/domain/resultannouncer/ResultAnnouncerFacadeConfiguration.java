package pl.lotto.domain.resultannouncer;

import pl.lotto.domain.resultchecker.ResultCheckerFacade;

public class ResultAnnouncerFacadeConfiguration {

    public ResultAnnouncerFacade createForTest(ResultCheckerFacade resultCheckerFacade, ResultAnnouncerRepository repository){
        return new ResultAnnouncerFacade(resultCheckerFacade, repository);
    }
}
