package br.lucasmsf.aded.game.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Dice {
    private static final Random random = new Random();

    public static int roll(int faces) {
        var value = random.nextInt(faces - 1) + 1;

        log.info(String.format("Dice Roll - Faces: %d Value: %d", faces, value));
        return value;

    }

    public static int roll(int faces, int quantity) {
        var sum = 0;

        for (int i = 0; i < quantity; i++) {
            sum += roll(faces);
        }

        log.info(String.format("Dice Roll Finish - Quantity: %d Faces: %d", quantity, faces));
        return sum;
    }
}
