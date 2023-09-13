package br.lucasmsf.aded.game;

import br.lucasmsf.aded.game.dto.GameRequest;
import br.lucasmsf.aded.game.dto.GameResponse;
import br.lucasmsf.aded.game.dto.GameTurnStartResponse;
import br.lucasmsf.aded.game.dto.TurnActionResponse;
import br.lucasmsf.aded.history.entity.HistoryTurn;
import br.lucasmsf.aded.history.enumerable.TurnAction;
import br.lucasmsf.aded.history.service.HistoryService;
import br.lucasmsf.aded.history.service.HistoryTurnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/games")
@Tag(name = "Games", description = "Game interactions and actions")
public class GameController {
    private final GameService gameService;
    private final HistoryTurnService historyTurnService;
    private final HistoryService historyService;
    private final ModelMapper modelMapper;

    @PostMapping
    @Operation(summary = "Create a new game")
    public ResponseEntity<GameResponse> create(
            @RequestBody @Valid GameRequest gameRequest
    ) {
        var game = this.gameService.create(
                gameRequest.getPlayerName(),
                gameRequest.getPlayerCharacterId(),
                gameRequest.getCpuCharacterId()
        );

        var gameResponse = this.modelMapper.map(game, GameResponse.class);

        return new ResponseEntity<>(gameResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/start")
    @Operation(summary = "Start a created game")
    public ResponseEntity<GameTurnStartResponse> startGame(
            @PathVariable Long id
    ) {
        var history = this.historyService.startGame(id);
        var historyTurn = this.historyTurnService.getCurrentTurn(history);
        var gameTurnStartResponse = new GameTurnStartResponse(){{
            setAttack(historyTurn.getAttackingCharacter().getName());
            setDefend(historyTurn.getDefenderCharacter().getName());
        }};
        return new ResponseEntity<>(gameTurnStartResponse, HttpStatus.OK);
    }

    private ResponseEntity<TurnActionResponse> turnAction(HistoryTurn historyTurn) {
        var turnActionResponse = this.modelMapper.map(historyTurn, TurnActionResponse.class);
        return new ResponseEntity<>(turnActionResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/attack")
    @Operation(summary = "Attack on current turn")
    public ResponseEntity<TurnActionResponse> attack(
            @PathVariable Long id
    ) {
        var historyTurn = this.historyTurnService.startTurn(id, TurnAction.ATTACK);
        return this.turnAction(historyTurn);
    }

    @PostMapping(value = "/{id}/defense")
    @Operation(summary = "Defense on current turn")
    public ResponseEntity<TurnActionResponse> defense(
            @PathVariable Long id
    ) {
        var historyTurn = this.historyTurnService.startTurn(id, TurnAction.DEFENSE);
        return this.turnAction(historyTurn);
    }

}
