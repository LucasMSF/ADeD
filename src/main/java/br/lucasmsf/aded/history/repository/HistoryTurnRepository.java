package br.lucasmsf.aded.history.repository;

import br.lucasmsf.aded.history.entity.HistoryTurn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryTurnRepository extends JpaRepository<HistoryTurn, Long> {
    @Query("SELECT h FROM HistoryTurn h WHERE h.history.id = :historyId ORDER BY h.id DESC LIMIT 1")
    HistoryTurn findLatestHistoryTurnByHistoryId(Long historyId);
}
