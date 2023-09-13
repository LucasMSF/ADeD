package br.lucasmsf.aded.game;

import br.lucasmsf.aded.application.dto.MessageResponse;
import br.lucasmsf.aded.game.exception.CharacterTypeNotAllowedException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GameControlleAdvice {

    @ExceptionHandler(value = CharacterTypeNotAllowedException.class)
    public ResponseEntity<MessageResponse>
    handleCharacterTypeNotAllowedException(
            CharacterTypeNotAllowedException ex
    ) {
        var message = new MessageResponse();
        message.setMessage(ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
