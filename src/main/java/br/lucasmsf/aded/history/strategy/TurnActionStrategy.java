package br.lucasmsf.aded.history.strategy;

import br.lucasmsf.aded.history.entity.HistoryTurn;
import br.lucasmsf.aded.history.enumerable.TurnAction;

public interface TurnActionStrategy {
    void execute(HistoryTurn historyTurn, TurnAction turnAction);
}
