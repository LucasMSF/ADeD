package br.lucasmsf.aded.history.strategy.impl;

import br.lucasmsf.aded.history.entity.HistoryTurn;
import br.lucasmsf.aded.history.exception.HasNoDamageException;
import br.lucasmsf.aded.history.strategy.CalculateDamageStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HasDamageStrategy implements CalculateDamageStrategy {
    private boolean hasDamage(HistoryTurn historyTurn) {
        var attack = historyTurn.getAttack();
        var defense = historyTurn.getDefense();
        if (attack == null || defense == null) {
            return false;
        }
        return historyTurn.getAttack() > historyTurn.getDefense();
    }

    @Override
    public void execute(HistoryTurn historyTurn) {
        if (!this.hasDamage(historyTurn)) {
            throw new HasNoDamageException();
        }
    }
}
