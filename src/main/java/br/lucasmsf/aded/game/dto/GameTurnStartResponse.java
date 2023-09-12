package br.lucasmsf.aded.game.dto;

import lombok.Data;

@Data
public class GameTurnStartResponse {
    private String attack;
    private String defend;
}
