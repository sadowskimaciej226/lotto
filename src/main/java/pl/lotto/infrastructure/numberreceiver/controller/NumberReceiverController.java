package pl.lotto.infrastructure.numberreceiver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.domain.numberreciver.NumberReceiverFacade;
import pl.lotto.domain.numberreciver.dto.InputNumbersResultDto;

@RestController
@RequiredArgsConstructor
class NumberReceiverController {
    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping("/inputNumbers")
    ResponseEntity<InputNumbersResultDto> inputNumbers(@RequestBody NumbersToInput numbersToInput){
        InputNumbersResultDto inputNumbersResultDto = numberReceiverFacade.inputNumbers(numbersToInput.inputNumbers());
        return ResponseEntity.ok(inputNumbersResultDto);
    }
}
