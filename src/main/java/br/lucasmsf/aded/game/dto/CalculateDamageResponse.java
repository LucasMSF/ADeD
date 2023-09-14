package br.lucasmsf.aded.game.dto;

import lombok.Data;

@Data
public class CalculateDamageResponse {
    private String defender;
    private String Attacker;
    private Integer damage;
    private Integer playerHpRemaining;
    private Integer cpuHpRemaining;
    private boolean haveNextTurn;
}
