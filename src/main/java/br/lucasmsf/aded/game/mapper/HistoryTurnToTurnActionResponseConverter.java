package br.lucasmsf.aded.game.mapper;

import br.lucasmsf.aded.game.dto.AttackResponse;
import br.lucasmsf.aded.game.dto.DefenseResponse;
import br.lucasmsf.aded.game.dto.TurnActionResponse;
import br.lucasmsf.aded.history.entity.HistoryTurn;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class HistoryTurnToTurnActionResponseConverter
        implements Converter<HistoryTurn, TurnActionResponse> {
    @Override
    public TurnActionResponse convert(MappingContext<HistoryTurn, TurnActionResponse> context) {
        var source = context.getSource();
        var destination = context.getDestination();

        if(destination == null) {
            destination = new TurnActionResponse();
        }

        var attack = new AttackResponse(){{
            setAttack(source.getAttack());
            setCharacter(source.getAttackingCharacter().getName());
        }};
        var defense = new DefenseResponse(){{
            setDefense(source.getDefense());
            setCharacter(source.getDefenderCharacter().getName());
        }};

        var hasDamage = attack.getAttack() > defense.getDefense();

        destination.setAttack(attack);
        destination.setDefense(defense);
        destination.setHasDamage(hasDamage);

        return destination;
    }
}
