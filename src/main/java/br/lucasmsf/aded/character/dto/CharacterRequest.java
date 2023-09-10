package br.lucasmsf.aded.character.dto;

import br.lucasmsf.aded.character.enumerable.CharacterType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class CharacterRequest extends CharacterDto {
}