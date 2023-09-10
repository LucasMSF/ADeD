package br.lucasmsf.aded.character;

import br.lucasmsf.aded.character.enumerable.CharacterType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer hp;
    private Integer strength;
    private Integer defense;
    private Integer agility;
    private Integer quantityOfDice;
    private Integer facesOfTheDice;
    @Enumerated(value = EnumType.STRING)
    private CharacterType type;
}
