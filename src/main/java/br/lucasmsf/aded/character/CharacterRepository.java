package br.lucasmsf.aded.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query(value = "SELECT * FROM character c WHERE c.type = 'MONSTER' ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Character findRandomMonsterCharacter();
}
