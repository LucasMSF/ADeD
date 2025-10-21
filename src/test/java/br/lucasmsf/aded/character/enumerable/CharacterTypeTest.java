package br.lucasmsf.aded.character.enumerable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTypeTest {
    @Test
    void doesCharacterTypeHaveTypesRequired() {
        assertAll("Does it have all types required",
                () -> assertEquals("HERO", CharacterType.HERO.name()),
                () -> assertEquals("MONSTER", CharacterType.MONSTER.name())
        );
    }
}
