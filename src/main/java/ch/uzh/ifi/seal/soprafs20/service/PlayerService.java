package ch.uzh.ifi.seal.soprafs20.service;

import ch.uzh.ifi.seal.soprafs20.constant.GameStatus;
import ch.uzh.ifi.seal.soprafs20.entity.Game;
import ch.uzh.ifi.seal.soprafs20.entity.RealPlayer;
import ch.uzh.ifi.seal.soprafs20.entity.User;
import ch.uzh.ifi.seal.soprafs20.exceptions.Game.GameFullException;
import ch.uzh.ifi.seal.soprafs20.exceptions.Game.PlayerAlreadyInGameException;
import ch.uzh.ifi.seal.soprafs20.exceptions.Game.PlayerNotInGameException;
import ch.uzh.ifi.seal.soprafs20.exceptions.Player.PlayerNotFoundException;
import ch.uzh.ifi.seal.soprafs20.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PlayerService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final PlayerRepository playerRepository;


    @Autowired
    public PlayerService(@Qualifier("playerRepository") PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<RealPlayer> getPlayersByGame(Game game) {
        return playerRepository.findRealPlayersByGame(game);
    }




    public RealPlayer getPlayerByPlayerId(Long playerId) {
        RealPlayer player = playerRepository.findRealPlayerByPlayerId(playerId);
        if(player==null){
            throw new PlayerNotFoundException(" with PlayerId: " + playerId.toString());
        }
        return player;
    }

    /**
     * Persists a player into table T_PLAYERS
     * (works only on RealPlayers atm)
     * @param player to be persisted as a player
     * @param game the user wants to join
     * @return Player
     */
    public RealPlayer createPlayer(RealPlayer player, Game game) {

        //CompleteDetails
        player.setGame(game);

        // saves the given entity but data is only persisted in the database once flush() is called
        playerRepository.save(player);
        playerRepository.flush();

        log.debug("Created Information for Player: {}", player);
        return player;
    }


    public void removePlayer( Game game, Long userId){

        List<RealPlayer> players = playerRepository.findRealPlayersByGameAndUserId(game,userId);

        //exception if player is not in the game
        if (players.isEmpty()) {
            throw new PlayerNotInGameException("UserId: " + userId.toString());
        }
        for(RealPlayer player:players) {
            playerRepository.delete(player);
            int initialPlayerCount = game.getPlayerCount();
            game.setPlayerCount(initialPlayerCount-1);
        }
    }
    /**
     * Persists a player into table T_PLAYERS and add it to game
     * (works only on RealPlayers atm)
     * @param player to be persisted as a player
     * @param game the user wants to join
     * @param user the User to join a game
     * @return Player
     */
    public Game addPlayer(Game game, RealPlayer player, User user) {

        //exception if game already has five players
        if(game.getPlayers().size() >= 5) {
            throw new GameFullException("Game already has five players.");
        }

        //exception if player is already in a game
        List<RealPlayer> playersOfUser = playerRepository.findRealPlayersByUserId(player.getUserId());
        //check if one of the players of this user is in an game with status != finished
        for(RealPlayer playerOfUser : playersOfUser){
            Game gameOfPlayer = playerOfUser.getGame();
            if(gameOfPlayer.getGameStatus() != GameStatus.FINISHED) {
                throw new PlayerAlreadyInGameException(" User with UserId: " + player.getUserId().toString()+" is in Game with GameId: "+gameOfPlayer.getGameId());
            }
        }

        List<RealPlayer> players = game.getPlayers();
        players.add(player);
        game.setPlayers(players);
        int initialPlayerCount = game.getPlayerCount();
        game.setPlayerCount(initialPlayerCount+1);
        player.setGame(game);
        player.setUserName(user.getUsername());
        playerRepository.save(player);
        playerRepository.flush();
        log.debug("Added player: {} to game: {}", player, game);
        return game;
    }

    /**
     * sets individual score of a player
     * @param playerId
     * @param score
     * @return
     */
    public RealPlayer setScore(Long playerId, int score) {
        //get player
        RealPlayer player = playerRepository.findRealPlayerByPlayerId(playerId);

        //set score
        int currentScore = player.getScore();
        currentScore = currentScore + score;
        player.setScore(currentScore);
        playerRepository.save(player);
        playerRepository.flush();

        return player;
    }

    /**
     * removes all player from a game
     * @param game
     * @return game without players
     */
    public Game removeAllPlayer(Game game) {
        List<RealPlayer> players = getPlayersByGame(game);
        for(RealPlayer player : players){
            removePlayer(game,player.getUserId());
        }
        return game;
    }
    /**
     * sum up all individual scores from players with one UserId
     */
    public int getTotalIndividualScore(long userId){
        List<RealPlayer> players = playerRepository.findRealPlayersByUserId(userId);
        int totalScore = 0;
        for(RealPlayer player: players){
            totalScore = totalScore + player.getScore();
        }
        return totalScore;
    }
    /**
     * get number of games from players with one UserId
     */
    public int getNumberOfPlayedGames(long userId){
        List<RealPlayer> players = playerRepository.findRealPlayersByUserId(userId);
        return players.size();
    }
    /**
     * sum up all game scores from players with one UserId
     */
    public int getTotalGameScore(long userId){
        List<RealPlayer> players = playerRepository.findRealPlayersByUserId(userId);
        int totalScore = 0;
        for(RealPlayer player: players){
            totalScore = totalScore + player.getGame().getScore();
        }
        return totalScore;
    }

    public RealPlayer removeScore(RealPlayer player, int score) {
        int currentScore = player.getScore();
        int newScore = currentScore - score;
        player.setScore(newScore);
        playerRepository.save(player);
        playerRepository.flush();
        return player;
    }
}
