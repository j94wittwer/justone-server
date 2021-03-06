package ch.uzh.ifi.seal.soprafs20.service;

import ch.uzh.ifi.seal.soprafs20.constant.GameMode;
import ch.uzh.ifi.seal.soprafs20.constant.GameStatus;
import ch.uzh.ifi.seal.soprafs20.constant.Role;
import ch.uzh.ifi.seal.soprafs20.entity.Game;
import ch.uzh.ifi.seal.soprafs20.entity.RealPlayer;
import ch.uzh.ifi.seal.soprafs20.exceptions.Game.GameNotFoundException;
import ch.uzh.ifi.seal.soprafs20.exceptions.Game.NotEnoughPlayersException;
import ch.uzh.ifi.seal.soprafs20.repository.GameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Game Service
 * This class is the "worker" and responsible for all functionality related to the game
 * (e.g., it creates, modifies, deletes, finds). The result will be passed back to the caller.
 */
@Service
@Transactional
public class GameService {
    private final Logger log = LoggerFactory.getLogger(GameService.class);

    private final GameRepository gameRepository;


    @Autowired
    public GameService(@Qualifier("gameRepository") GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Returns a list of all games from table T_GAMES
     * @return List<Game>
     */
    public List<Game> getGames() {
        return this.gameRepository.findAll();
    }

    /**
     * Returns game with given id from table "T_GAMES"
     * @param id of the game to be returned
     * @return Game
     */
    public Game getGame(Long id){
        Game game = gameRepository.findGameByGameId(id);

        if(game == null)
            throw new GameNotFoundException("Id: "+id.toString());

        return game;
    }

    /**
     * Persists a game into table T_GAMES
     * @param game to be persisted
     * @return Game
     */
    public Game createGame(Game game) {

        //CompleteDetails
        game.setGameStatus(GameStatus.INITIALIZED);
        game.setDateCreated(LocalDate.now());
        //set start score to zero
        game.setScore(0);
        //set creator
        game.setCreatorUsername(game.getCreatorUsername());

        //if gameMode is not specified, set to STANDARD
        if(game.getGameMode()==null){game.setGameMode(GameMode.STANDARD);}

        //if gameName is not specified, set to "Game+ unique integer"
        Date date = new Date();
        if(game.getGameName()==null){game.setGameName("Game"+date.hashCode());}


        gameRepository.save(game);
        gameRepository.flush();

        log.debug("Created Information for Game: {}", game);
        return game;
    }


    public void removeGame(Game game) {
        gameRepository.delete(game);
    }

    /**
     * starts a game if it exists
     *
     * @param game to be started
     * @return Game
     */
    public Game startGame(Game game) {

        game.setGameStatus(GameStatus.RUNNING);
        //set Role of players if there are more than minPlayers player
        List<RealPlayer> players = game.getPlayers();
        int minPlayers = 2;
        if (players.size() < minPlayers) {
            throw new NotEnoughPlayersException("There are only " + players.size() + " Players in the Game." + minPlayers);
        }
        //set all players to ROLE.CLUE_WRITER
        for (RealPlayer player : players) {
            player.setRole(Role.CLUE_WRITER);
        }
        //set one player to ROLE.GUESSER
        players.iterator().next().setRole(Role.GUESSER);

        //store changes
        gameRepository.save(game);
        gameRepository.flush();

        return game;
    }

    /**
     * finishes game
     * @param game
     * @return
     */
    public Game finishGame(Game game) {
        game.setGameStatus(GameStatus.FINISHED);
        //store changes
        gameRepository.save(game);
        gameRepository.flush();

        return game;
    }

    /**
     * increase game score by 1
     * @param game
     */
    public void increaseScore(Game game) {
        int score = game.getScore();
        score = score + 1;
        game.setScore(score);
    }
}
