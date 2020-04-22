package ch.uzh.ifi.seal.soprafs20.rest.mapper;

import ch.uzh.ifi.seal.soprafs20.entity.*;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Clue.ClueGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Clue.CluePostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Game.GameGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Game.GamePostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Guess.GuessGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Guess.GuessPostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Player.PlayerGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Player.PlayerPutDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Round.RoundGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.User.UserGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.User.UserPostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.User.UserPutDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.WordCard.WordCardPutDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


/**
 * DTOMapper
 * This class is responsible for generating classes that will automatically transform/map the internal representation
 * of an entity (e.g., the User) to the external/API representation (e.g., UserGetDTO for getting, UserPostDTO for creating)
 * and vice versa.
 * Additional mappers can be defined for new entities.
 * Always created one mapper for getting information (GET) and one mapper for creating information (POST).
 */
@Mapper
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    //id is auto-generated
    @Mapping(target = "id", ignore = true)
    //token is generated by system
    @Mapping(target = "token", ignore = true)
    //status is set to OFFLINE on creation
    @Mapping(target = "status", ignore = true)
    //dateCreated is generated by system
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "password", target = "password")
    User convertUserPostDTOtoUserEntity(UserPostDTO userPostDTO);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "dateCreated", target = "dateCreated")
    @Mapping(source = "token", target = "token")
    UserGetDTO convertUserEntityToUserGetDTO(User user);

    //these properties are ignored because they are not set by the user
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)

    @Mapping(target = "password", ignore = true)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "name", target = "name")
    User convertUserPutDTOtoEntity(UserPutDTO userPutDTO);

    @Mapping(source = "gameId", target = "gameId")
    @Mapping(source = "gameName", target = "gameName")
    @Mapping(source = "dateCreated", target = "dateCreated")
    @Mapping(source = "playerCount", target = "playerCount")
    @Mapping(source = "gameStatus", target = "gameStatus")
    @Mapping(source = "gameMode", target = "gameMode")
    @Mapping(source = "score", target = "score")
    GameGetDTO convertGameEntityToGameGetDTO(Game game);

    //gameId is auto-generated
    @Mapping(target = "gameId", ignore = true)
    //gameStatus is set to INITIALIZED on creation
    @Mapping(target = "gameStatus", ignore = true)
    //score is generated by the system
    @Mapping(target = "score", ignore = true)
    @Mapping(source = "gameName", target = "gameName")
    @Mapping(source = "gameMode", target = "gameMode")
    Game convertGamePostDTOtoGameEntity(GamePostDTO gamePostDTO);


    @Mapping(source = "roundId", target = "roundId")
    //@Mapping(source = "game", target = "gameId")
    @Mapping(source = "roundNum", target = "roundNum")
    @Mapping(source = "roundStatus", target = "roundStatus")
    //@Mapping(source = "guess", target = "guess")

    @Mapping(source ="wordCard", target = "wordCard")
    RoundGetDTO convertRoundEntityToRoundGetDTO(Round round);

    @Mapping(source = "playerId", target = "playerId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "userName", target  = "userName")
    @Mapping(source = "role", target = "role")
    PlayerGetDTO convertPlayerEntityToPlayerGetDTO(RealPlayer player);

    @Mapping(source = "userId", target = "userId")
    RealPlayer convertPlayerPutDTOtoPlayerEntity(PlayerPutDTO playerPutDTO);


    @Mapping(source = "word", target = "word")
    Guess convertGuessPostDTOtoGuessEntity(GuessPostDTO guessPostDTO);
    
    @Mapping(source = "guessId", target = "guessId")
    @Mapping(source = "word", target = "word")
    @Mapping(source = "isValid", target = "isValid")
    GuessGetDTO convertGuessEntityToGuessGetDTO(Guess guess);


    @Mapping(source = "selectedWord", target = "selectedWord")
    WordCard convertWordCardPutDTOtoWordCardEntity(WordCardPutDTO wordCardPutDTO);

    @Mapping(source = "word", target = "word")
    Clue convertCluePostDTOtoClueEntity(CluePostDTO cluePostDTO);

    @Mapping(source = "clueId", target = "clueId")
    @Mapping(source = "word", target = "word")
    @Mapping(source = "isValid", target = "isValid")
    ClueGetDTO convertClueEntityToClueGetDTO(Clue clue);

}
