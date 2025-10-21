package br.lucasmsf.aded.application.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    @Test
    void rollShouldRespectFacesTest() {
        int faces = 5;

        int value = Dice.roll(faces);

        assertAll("Value between interval",
                () -> assertTrue(value > 0, "Must be greater than 0"),
                () -> assertTrue(value < 6, "Must be less than 6")
        );
    }

    @Test
    void rollShouldRespectFacesAndQuantityTest() {
        int faces = 5;
        int quantity = 2;

        int value = Dice.roll(faces, quantity);

        assertAll("Value between interval",
                () -> assertTrue(value > 1, "Must be greater than 1"),
                () -> assertTrue(value < 11, "Must be less than 11")
        );
    }
}
