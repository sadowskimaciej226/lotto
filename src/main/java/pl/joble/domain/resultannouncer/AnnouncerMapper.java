package pl.joble.domain.resultannouncer;

import pl.joble.domain.resultannouncer.dto.AnnouncerResponseDto;
import pl.joble.domain.resultchecker.dto.PlayerDto;

class AnnouncerMapper {
    private AnnouncerMapper(){}

    public static AnnouncerResponse mapFromDtoToEntity(PlayerDto byId) {
        return AnnouncerResponse.builder()
                .wonNumbers(byId.wonNumbers())
                .inputtedNumbers(byId.inputtedNumbers())
                .drawDate(byId.drawDate())
                .ticketId(byId.id())
                .hitNumbers(byId.hitNumbers())
                .build();
    }
    public static AnnouncerResponseDto mapFromEntityToDto(AnnouncerResponse response) {
        return AnnouncerResponseDto.builder()
                .wonNumbers(response.wonNumbers())
                .inputtedNumbers(response.inputtedNumbers())
                .drawDate(response.drawDate())
                .ticketId(response.ticketId())
                .hitNumbers(response.hitNumbers())
                .build();
    }

}