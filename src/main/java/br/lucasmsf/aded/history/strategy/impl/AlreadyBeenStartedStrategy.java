package br.lucasmsf.aded.history.strategy.impl;

import br.lucasmsf.aded.game.Game;
import br.lucasmsf.aded.history.exception.GameAlreadyBeenStartedException;
import br.lucasmsf.aded.history.repository.HistoryRepository;
import br.lucasmsf.aded.history.strategy.StartGameStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AlreadyBeenStartedStrategy implements StartGameStrategy {
    private final HistoryRepository historyRepository;

    @Override
    public void execute(Game game) {
        if(this.historyRepository.existsByGameId(game.getId())) {
            throw new GameAlreadyBeenStartedException(game);
        }
    }
}
