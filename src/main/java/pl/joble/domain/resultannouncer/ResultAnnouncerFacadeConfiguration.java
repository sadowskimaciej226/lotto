package pl.joble.domain.resultannouncer;

import pl.joble.domain.resultchecker.ResultCheckerFacade;

public class ResultAnnouncerFacadeConfiguration {

    public ResultAnnouncerFacade createForTest(ResultCheckerFacade resultCheckerFacade, ResultAnnouncerRepository repository){
        return new ResultAnnouncerFacade(resultCheckerFacade, repository);
    }
}
