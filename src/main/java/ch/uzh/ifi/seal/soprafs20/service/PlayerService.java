package ch.uzh.ifi.seal.soprafs20.service;

import ch.uzh.ifi.seal.soprafs20.entity.Player;
import ch.uzh.ifi.seal.soprafs20.entity.RealPlayer;
import ch.uzh.ifi.seal.soprafs20.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PlayerService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(@Qualifier("playerRepository") PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Persists a player into table T_PLAYERS
     * (works only on RealPlayers atm)
     * @param player to be persisted
     * @return Player
     */
    public Player createPlayer(RealPlayer player) {

        //CompleteDetails
        player.setUser(player.getUser());

        // saves the given entity but data is only persisted in the database once flush() is called
        playerRepository.save(player);
        playerRepository.flush();

        log.debug("Created Information for Player: {}", player);
        return player;
    }
}
