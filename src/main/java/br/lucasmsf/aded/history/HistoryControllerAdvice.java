package br.lucasmsf.aded.history;

import br.lucasmsf.aded.application.dto.MessageResponse;
import br.lucasmsf.aded.application.exception.BaseException;
import br.lucasmsf.aded.history.exception.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class HistoryControllerAdvice {
    @ExceptionHandler(value = {
            GameAlreadyBeenStartedException.class,
            GameNotStartedException.class,
            TurnAlreadyBeenStartedException.class,
            IncorrectTurnActionException.class,
            HasNoDamageException.class
    })
    public ResponseEntity<MessageResponse>
    handleBaseException(BaseException ex) {
        var message = new MessageResponse();
        message.setMessage(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
