package pl.joble.domain.resultchecker;

import pl.joble.domain.numberreciver.dto.TicketDto;
import pl.joble.domain.resultchecker.dto.WinnersDto;

class Mapper {
    private Mapper(){}
    public static Winner mapFromTicketToWinner(TicketDto ticketDto) {
        return Winner.builder()
                .id(ticketDto.ticketId())
                .wonNumbers(ticketDto.numbersFromUser())
                .drawDate(ticketDto.drawDate())
                .build();
    }

    public static WinnersDto mapFromEntityToDto(Winner winner) {
        return WinnersDto.builder()
                .drawDate(winner.drawDate())
                .wonNumbers(winner.wonNumbers())
                .message("Winners success to retrieve")
                .build();
    }
}
