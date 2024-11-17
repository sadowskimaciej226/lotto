package pl.lotto.infrastructure.numberreceiver.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

record NumbersToInput(
        @NotEmpty(message = "Input numbers can't be empty")
        @NotBlank(message = "Input numbers can't be blank")
        Set<Integer> inputNumbers) {
}
