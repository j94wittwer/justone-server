package ch.uzh.ifi.seal.soprafs20.repository;

import ch.uzh.ifi.seal.soprafs20.entity.Clue;
import ch.uzh.ifi.seal.soprafs20.entity.RealPlayer;
import ch.uzh.ifi.seal.soprafs20.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("clueRepository")
public interface ClueRepository extends JpaRepository<Clue, Long> {
    Clue getClueByClueId(Long clueId);
    Clue getClueByRound(Round round);
    List<Clue> getCluesByRound(Round round);
    List<Clue> getCluesByOwnerAndRound(RealPlayer owner, Round round);
    Clue getClueByOwnerAndRound(RealPlayer owner, Round round);
    List<Clue> findAllByRoundOrderByTotalTimeAsc(Round round);
}
