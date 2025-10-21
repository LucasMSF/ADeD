package br.lucasmsf.aded.application.util;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class Dice {
    public static int roll(int faces) {
        var value = ThreadLocalRandom.current().nextInt(1, faces + 1) ;

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
