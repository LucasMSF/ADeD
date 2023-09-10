package br.lucasmsf.aded.character.dto;

import br.lucasmsf.aded.character.enumerable.CharacterType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CharacterDto {
    @NotNull
    private String name;
    @Digits(integer = 3, fraction = 0)
    private Integer hp;
    @Digits(integer = 3, fraction = 0)
    private Integer strength;
    @Digits(integer = 3, fraction = 0)
    private Integer defense;
    @Digits(integer = 3, fraction = 0)
    private Integer agility;
    @Digits(integer = 3, fraction = 0)
    private Integer quantityOfDice;
    @Digits(integer = 3, fraction = 0)
    private Integer facesOfTheDice;
    @NotNull
    private CharacterType type;
}
