package pl.joble.domain.numberreciver;

import java.util.Set;

class Validator {
    private static final int MAX_NUMBERS_FROM_USER = 6;
    private static final int MIN_VALUE_FROM_USER = 1;
    private static final int MAX_VALUE_FROM_USER = 99;
     boolean areNumbersCorrect(Set<Integer> numbersFromUser) {
        return numbersFromUser.stream()
                .filter(number -> number >= MIN_VALUE_FROM_USER)
                .filter(number -> number <= MAX_VALUE_FROM_USER)
                .count() ==MAX_NUMBERS_FROM_USER;
    }
}
