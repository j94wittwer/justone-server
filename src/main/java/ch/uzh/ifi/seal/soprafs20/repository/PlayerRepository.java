package ch.uzh.ifi.seal.soprafs20.repository;

import ch.uzh.ifi.seal.soprafs20.entity.Game;
import ch.uzh.ifi.seal.soprafs20.entity.RealPlayer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("playerRepository")
public interface PlayerRepository extends JpaRepository<RealPlayer, Long> {
    List<RealPlayer> findRealPlayersByGame(Game game);
    RealPlayer findRealPlayerByUserId(Long userId);
    List<RealPlayer> findRealPlayersByUserId(Long userId);
    List<RealPlayer> findRealPlayersByGameAndUserId(Game game , Long userId);
    RealPlayer findRealPlayerByPlayerId(Long playerId);

}
