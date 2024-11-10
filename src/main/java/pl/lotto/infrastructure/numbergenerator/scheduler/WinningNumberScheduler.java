package pl.lotto.infrastructure.numbergenerator.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.lotto.domain.numbergenerator.NumberGeneratorFacade;


@Component
@RequiredArgsConstructor
@Log4j2
public class WinningNumberScheduler {
    private final NumberGeneratorFacade numberGeneratorFacade;

    @Scheduled(cron = "${lotto.number-generator.lotteryRunOccurrence}")
    void doSomething(){
        log.info("Scheduler started");
        numberGeneratorFacade.generateWinningNumbers();
    }

}
