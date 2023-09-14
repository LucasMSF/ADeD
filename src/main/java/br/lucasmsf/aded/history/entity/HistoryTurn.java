package br.lucasmsf.aded.history.entity;

import br.lucasmsf.aded.character.Character;
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
public class HistoryTurn implements Comparable<HistoryTurn> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private History history;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Character attackingCharacter;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Character defenderCharacter;
    private Integer attack;
    private Integer defense;
    private Integer damage;

    @Override
    public int compareTo(HistoryTurn o) {
        return this.id.compareTo(o.id);
    }
}
