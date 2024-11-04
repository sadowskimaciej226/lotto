package pl.joble.domain.resultchecker;

import pl.joble.domain.resultchecker.dto.PlayerDto;

class PlayerMapper {
    private PlayerMapper(){}


    public static PlayerDto mapFromEntityToDto(Player winner) {
        return PlayerDto.builder()
                .drawDate(winner.drawDate())
                .wonNumbers(winner.wonNumbers())
                .hitNumbers(winner.hitNumbers())
                .inputtedNumbers(winner.inputtedNumbers())
                .build();
    }

    public static Player mapFromDtoToEntity(PlayerDto playerDto) {
        return Player.builder()
                .id(playerDto.id())
                .drawDate(playerDto.drawDate())
                .hitNumbers(playerDto.hitNumbers())
                .inputtedNumbers(playerDto.inputtedNumbers())
                .wonNumbers(playerDto.wonNumbers())
                .build();
    }
}
