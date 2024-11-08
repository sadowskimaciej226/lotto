package pl.lotto.domain.numbergenerator;

import pl.lotto.domain.numbergenerator.dto.SixRandomNumbersDto;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class RandomNumberGenerator implements RandomNumberGenerable{
    private Random random = new Random();
    private Set<Integer> randomNumbers = new HashSet();

    @Override
    public SixRandomNumbersDto generate6RandomNumbers(int count, int lowerBand, int upperBand) {
        while(randomNumbers.size()!=6){
            randomNumbers.add(random.nextInt(1,100));
        }
        return SixRandomNumbersDto.builder()
                .randomNumbers(randomNumbers)
                .build();
    }
}
