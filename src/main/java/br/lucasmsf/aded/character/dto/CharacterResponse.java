package br.lucasmsf.aded.character.dto;

import br.lucasmsf.aded.character.enumerable.CharacterType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CharacterResponse extends CharacterDto {
    private Long id;
}