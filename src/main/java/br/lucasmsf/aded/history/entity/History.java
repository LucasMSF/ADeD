package br.lucasmsf.aded.history.entity;

import br.lucasmsf.aded.character.Character;
import br.lucasmsf.aded.game.Game;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Game game;
    @ManyToOne
    private Character startCharacter;
    @OneToMany(mappedBy = "history")
    private List<HistoryTurn> turns;
}
