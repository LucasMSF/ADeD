package br.lucasmsf.aded.game.mapper;

import br.lucasmsf.aded.game.dto.CalculateDamageResponse;
import br.lucasmsf.aded.history.entity.HistoryTurn;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class HistoryTurnToCalculateDamageResponseConverter
        implements Converter<HistoryTurn, CalculateDamageResponse> {
    @Override
    public CalculateDamageResponse convert(MappingContext<HistoryTurn, CalculateDamageResponse> context) {
        var source = context.getSource();
        var destination = context.getDestination();

        if(destination == null) {
            destination = new CalculateDamageResponse();
        }

        var game = source.getHistory().getGame();

        destination.setAttacker(source.getAttackingCharacter().getName());
        destination.setDefender(source.getDefenderCharacter().getName());
        destination.setDamage(source.getDamage());
        destination.setPlayerHpRemaining(game.getPlayerHp());
        destination.setCpuHpRemaining(game.getCpuHp());
        destination.setHaveNextTurn(game.getPlayerHp() > 0 && game.getCpuHp() > 0);

        return destination;
    }
}
