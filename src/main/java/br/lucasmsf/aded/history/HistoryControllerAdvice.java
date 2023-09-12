package br.lucasmsf.aded.history;

import br.lucasmsf.aded.application.dto.MessageResponse;
import br.lucasmsf.aded.history.exception.GameAlreadyBeenStartedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HistoryControllerAdvice {
    @ExceptionHandler(GameAlreadyBeenStartedException.class)
    public ResponseEntity<MessageResponse>
    handleGameAlreadyBeenStartedException(
            GameAlreadyBeenStartedException ex
    ) {
        var message = new MessageResponse();
        message.setMessage(ex.getMessage());

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
