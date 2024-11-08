package pl.lotto.domain.numbergenerator;

import pl.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;

public interface RandomNumberGenerable {

    public SixRandomNumbersDto generate6RandomNumbers(int count, int lowerBand, int upperBand);
}
