package br.lucasmsf.aded.history.mapper;

import br.lucasmsf.aded.game.dto.AttackResponse;
import br.lucasmsf.aded.game.dto.DefenseResponse;
import br.lucasmsf.aded.history.dto.HistoryTurnResponse;
import br.lucasmsf.aded.history.entity.HistoryTurn;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class HistoryTurnToHistoryTurnResponseConverter
        implements Converter<HistoryTurn, HistoryTurnResponse> {
    @Override
    public HistoryTurnResponse convert(MappingContext<HistoryTurn, HistoryTurnResponse> context) {
        var source = context.getSource();
        var destination = context.getDestination();

        if (destination == null) {
            destination = new HistoryTurnResponse();
        }

        var attack = new AttackResponse() {{
            setCharacter(source.getAttackingCharacter().getName());
            setAttack(source.getAttack());
        }};

        var defense = new DefenseResponse() {{
            setCharacter(source.getDefenderCharacter().getName());
            setDefense(source.getDefense());
        }};

        destination.setAttack(attack);
        destination.setDefense(defense);
        destination.setDamage(source.getDamage());

        return destination;
    }
}
