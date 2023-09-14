package br.lucasmsf.aded.history.dto;

import br.lucasmsf.aded.game.dto.GameResponse;
import lombok.Data;

import java.util.List;

@Data
public class HistoryResponse {
    private Long id;
    private GameResponse game;
    private List<HistoryTurnResponse> turns;
}
