package br.lucasmsf.aded.history.strategy.impl;

import br.lucasmsf.aded.history.entity.HistoryTurn;
import br.lucasmsf.aded.history.enumerable.TurnAction;
import br.lucasmsf.aded.history.exception.IncorrectTurnActionException;
import br.lucasmsf.aded.history.strategy.TurnActionStrategy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CorrectTurnActionStrategy implements TurnActionStrategy {
    @Override
    public void execute(HistoryTurn historyTurn, TurnAction turnAction) {
        var playerCharacter = historyTurn.getHistory().getGame().getPlayerCharacter();
        var correctAction = Objects.equals(
                playerCharacter.getId(),
                historyTurn.getAttackingCharacter().getId()
        )
                ? TurnAction.ATTACK
                : TurnAction.DEFENSE;

        if(correctAction != turnAction) {
            throw new IncorrectTurnActionException();
        }
    }
}
