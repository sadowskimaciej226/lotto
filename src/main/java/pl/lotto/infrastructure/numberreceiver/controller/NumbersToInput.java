package pl.lotto.infrastructure.numberreceiver.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

record NumbersToInput(

        @NotNull(message = "Input numbers can't be empty")
        @NotEmpty(message = "Input numbers can't be empty")
        Set<Integer> inputNumbers) {
}
