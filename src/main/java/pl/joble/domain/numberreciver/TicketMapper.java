package pl.joble.domain.numberreciver;

import pl.joble.domain.numberreciver.dto.TicketDto;

class TicketMapper {
    private TicketMapper() {
    }

    public static TicketDto mapToDto(Ticket ticket) {
        return TicketDto.builder()
                .ticketId(ticket.id())
                .numbersFromUser(ticket.numbers())
                .drawDate(ticket.drawDate())
                .build();
    }
}
