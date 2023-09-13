package br.lucasmsf.aded.game;

import br.lucasmsf.aded.character.Character;
import br.lucasmsf.aded.history.entity.History;
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
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String playerName;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Character playerCharacter;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Character cpuCharacter;
    private Integer playerHp;
    private Integer cpuHp;
    @OneToOne(mappedBy = "game")
    private History history;
}
