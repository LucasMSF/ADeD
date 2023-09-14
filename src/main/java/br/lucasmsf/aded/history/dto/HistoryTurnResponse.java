package br.lucasmsf.aded.history.dto;

import br.lucasmsf.aded.game.dto.AttackResponse;
import br.lucasmsf.aded.game.dto.DefenseResponse;
import lombok.Data;

@Data
public class HistoryTurnResponse {
    private AttackResponse attack;
    private DefenseResponse defense;
    private Integer damage;
}
