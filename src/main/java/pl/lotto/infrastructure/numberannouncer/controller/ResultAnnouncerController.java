package pl.lotto.infrastructure.numberannouncer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lotto.domain.resultannouncer.ResultAnnouncerFacade;
import pl.lotto.domain.resultannouncer.dto.AnnouncerResponseDto;

@RestController
@RequiredArgsConstructor
 class ResultAnnouncerController {
    private final ResultAnnouncerFacade resultAnnouncerFacade;
    @GetMapping("/result/{id}")
    ResponseEntity<AnnouncerResponseDto> findResultById(@PathVariable String id){
        AnnouncerResponseDto playerByTicketId = resultAnnouncerFacade.findPlayerByTicketId(id);
        return ResponseEntity.ok(playerByTicketId);
    }
}
