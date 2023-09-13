package br.lucasmsf.aded.game.dto;

import lombok.Data;

@Data
public class TurnActionResponse {
    private AttackResponse attack;
    private DefenseResponse defense;
    private boolean hasDamage;
}
