package pl.joble.domain.numbergenerator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

class RandomNumberGenerator {
    private Random random = new Random();
    private Set<Integer> randomNumbers = new HashSet();
    public Set<Integer> generate6RandomNumbers(){
        while(randomNumbers.size()!=6){
            randomNumbers.add(random.nextInt(1,100));
        }
        return randomNumbers;
    }
}
