package br.lucasmsf.aded.game.exception;

import br.lucasmsf.aded.application.exception.BaseException;
import br.lucasmsf.aded.character.enumerable.CharacterType;

public class CharacterTypeNotAllowedException extends BaseException {
    public CharacterTypeNotAllowedException(CharacterType notAllowedCharacterType) {
        super("Character of type " + notAllowedCharacterType.name() + " is not allowed");
    }
}
