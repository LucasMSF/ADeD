package br.lucasmsf.aded.history.strategy;

import br.lucasmsf.aded.history.entity.HistoryTurn;

public interface CalculateDamageStrategy {
    void execute(HistoryTurn historyTurn);
}
