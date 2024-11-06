package pl.lotto.domain.resultannouncer;

import lombok.RequiredArgsConstructor;
import pl.lotto.domain.resultannouncer.dto.AnnouncerResponseDto;
import pl.lotto.domain.resultchecker.ResultCheckerFacade;
import pl.lotto.domain.resultchecker.dto.PlayerDto;

@RequiredArgsConstructor
public class ResultAnnouncerFacade {
    private final ResultCheckerFacade resultCheckerFacade;
    private final ResultAnnouncerRepository announcerRepository;

    public AnnouncerResponseDto findPlayerByTicketId(String ticketId) {
        if(!announcerRepository.existsById(ticketId)) {
            PlayerDto byId = resultCheckerFacade.findById(ticketId);
            AnnouncerResponse response= announcerRepository.save(AnnouncerMapper.mapFromDtoToEntity(byId));
            return AnnouncerMapper.mapFromEntityToDto(response);
        } else throw new PlayerAlreadyCheckedException("Ticket was checked before");
    }




}
