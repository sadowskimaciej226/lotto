package pl.lotto.domain.numbergenerator;

import lombok.Builder;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Builder
@ConfigurationProperties(prefix = "lotto.number-generator")
public record WinningNumbersProperties(int count, int lowerBand, int upperBand) {
}
