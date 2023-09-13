package br.lucasmsf.aded.history.strategy.impl;

import br.lucasmsf.aded.history.entity.HistoryTurn;
import br.lucasmsf.aded.history.exception.TurnAlreadyBeenStartedException;
import br.lucasmsf.aded.history.strategy.StartTurnStrategy;
import org.springframework.stereotype.Component;

@Component
public class TurnAlreadyBeenStartedStrategy implements StartTurnStrategy {
    @Override
    public void execute(HistoryTurn historyTurn) {
        if(historyTurn.getAttack() != null || historyTurn.getDefense() != null) {
            throw new TurnAlreadyBeenStartedException();
        }
    }
}
