package pl.lotto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.lotto.domain.numbergenerator.WinningNumbersProperties;

@SpringBootApplication
@EnableConfigurationProperties({WinningNumbersProperties.class})
@EnableScheduling
public class LottoApplication {
    public static void main(String[] args) {
        SpringApplication.run(LottoApplication.class, args);
    }
}
