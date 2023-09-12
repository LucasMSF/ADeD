package br.lucasmsf.aded.history.service;

import br.lucasmsf.aded.character.Character;
import br.lucasmsf.aded.history.entity.History;
import br.lucasmsf.aded.history.entity.HistoryTurn;
import br.lucasmsf.aded.history.repository.HistoryTurnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HistoryTurnService {
    private final HistoryTurnRepository historyTurnRepository;

    public HistoryTurn create(
            History history,
            Character attackingCharacter,
            Character defenseCharacter
    ) {
        var historyTurn = new HistoryTurn();
        historyTurn.setHistory(history);
        historyTurn.setAttackingCharacter(attackingCharacter);
        historyTurn.setDefenderCharacter(defenseCharacter);

        return this.historyTurnRepository.save(historyTurn);
    }

    public HistoryTurn getCurrentTurn(History history) {
        return this.historyTurnRepository
                .findLatestHistoryTurnByHistoryId(history.getId());
    }

}
