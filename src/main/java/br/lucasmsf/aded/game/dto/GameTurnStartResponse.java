package br.lucasmsf.aded.game.dto;

import lombok.Data;

@Data
public class GameTurnStartResponse {
    private String attacker;
    private String defender;
}
