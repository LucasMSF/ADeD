package br.lucasmsf.aded.game.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class GameRequest {
    @NotNull
    private Long playerCharacterId;
    private Long cpuCharacterId;
}
