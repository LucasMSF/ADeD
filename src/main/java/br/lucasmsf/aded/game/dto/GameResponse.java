package br.lucasmsf.aded.game.dto;

import br.lucasmsf.aded.character.dto.CharacterResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class GameResponse {
    private Long id;
    private String playerName;
    private CharacterResponse playerCharacter;
    private CharacterResponse cpuCharacter;
}
