package br.lucasmsf.aded.history.strategy;

import br.lucasmsf.aded.history.entity.HistoryTurn;
import br.lucasmsf.aded.history.enumerable.TurnAction;

public interface StartTurnStrategy {
    void execute(HistoryTurn historyTurn);
}
