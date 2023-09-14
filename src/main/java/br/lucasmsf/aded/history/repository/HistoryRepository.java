package br.lucasmsf.aded.history.repository;

import br.lucasmsf.aded.history.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("SELECT CASE WHEN count(h) > 0 THEN true ELSE false END FROM History h WHERE h.game.id = :gameId")
    boolean existsByGameId(Long gameId);

    @Query("SELECT h FROM History h WHERE h.game.id = :gameId")
    Optional<History> findByGameId(Long gameId);
}
