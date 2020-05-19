package ch.uzh.ifi.seal.soprafs20.rest.mapper;

import ch.uzh.ifi.seal.soprafs20.entity.*;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Clue.ClueGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Clue.CluePostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Game.GameGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Game.GamePostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Guess.GuessGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Guess.GuessPostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Message.MessageGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Message.MessagePostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Player.PlayerGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Player.PlayerPutDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Round.RoundGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.User.UserGetDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.User.UserPostDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.User.UserPutDTO;
import ch.uzh.ifi.seal.soprafs20.rest.dto.Vote.VotePutDTO;
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
    //This is not bad practice referring to mapstruct.org, although sonar cube complains

    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    //id is auto-generated
    @Mapping(target = "id", ignore = true)
    //token is generated by system
    @Mapping(target = "token", ignore = true)
    //status is set to OFFLINE on creation
    @Mapping(target = "status", ignore = true)
    //dateCreated is generated by system
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "nrOfPlayedGames", ignore = true)
    @Mapping(target = "totalGameScore", ignore = true)
    @Mapping(target = "totalIndividualScore", ignore = true)
    @Mapping(target = "icon", ignore = true)
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
    @Mapping(source = "icon", target = "icon")
    @Mapping(source = "nrOfPlayedGames", target = "nrOfPlayedGames")
    @Mapping(source = "totalGameScore", target = "totalGameScore")
    @Mapping(source = "totalIndividualScore", target = "totalIndividualScore")
    UserGetDTO convertUserEntityToUserGetDTO(User user);

    //these properties are ignored because they are not set by the user
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "token", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "nrOfPlayedGames", ignore = true)
    @Mapping(target = "totalGameScore", ignore = true)
    @Mapping(target = "totalIndividualScore", ignore = true)
    @Mapping(source = "username", target = "username")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "icon", target = "icon")
    User convertUserPutDTOtoEntity(UserPutDTO userPutDTO);

    @Mapping(source = "gameId", target = "gameId")
    @Mapping(source = "gameName", target = "gameName")
    @Mapping(source = "dateCreated", target = "dateCreated")
    @Mapping(source = "playerCount", target = "playerCount")
    @Mapping(source = "gameStatus", target = "gameStatus")
    @Mapping(source = "gameMode", target = "gameMode")
    @Mapping(source = "botMode", target = "botMode")
    @Mapping(source = "duration", target = "duration")
    @Mapping(source = "score", target = "score")
    GameGetDTO convertGameEntityToGameGetDTO(Game game);

    //gameId is auto-generated
    @Mapping(target = "gameId", ignore = true)
    //gameStatus is set to INITIALIZED on creation
    @Mapping(target = "gameStatus", ignore = true)
    //score is generated by the system
    @Mapping(target = "score", ignore = true)
    @Mapping(target = "playerCount", ignore = true)
    @Mapping(target = "rounds", ignore = true)
    @Mapping(target = "players", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(source = "gameName", target = "gameName")
    @Mapping(source = "gameMode", target = "gameMode")
    @Mapping(source = "botMode", target = "botMode")
    @Mapping(source = "duration", target = "duration")
    Game convertGamePostDTOtoGameEntity(GamePostDTO gamePostDTO);


    @Mapping(source = "roundId", target = "roundId")
    //@Mapping(source = "game", target = "gameId")
    @Mapping(source = "roundNum", target = "roundNum")
    @Mapping(source = "roundStatus", target = "roundStatus")
    @Mapping(source = "guess", target = "guess")
    @Mapping(source = "wordCard", target = "wordCard")
    @Mapping(source = "clues", target = "clues")
    RoundGetDTO convertRoundEntityToRoundGetDTO(Round round);

    @Mapping(source = "playerId", target = "playerId")
    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "userName", target  = "userName")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "score", target = "score")
    PlayerGetDTO convertPlayerEntityToPlayerGetDTO(RealPlayer player);

    @Mapping(source = "userId", target = "userId")
    @Mapping(target = "playerId", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "game", ignore = true)
    @Mapping(target = "score", ignore = true)
    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "guessList", ignore = true)
    @Mapping(target = "clues", ignore = true)
    RealPlayer convertPlayerPutDTOtoPlayerEntity(PlayerPutDTO playerPutDTO);


    @Mapping(source = "word", target = "word")
    @Mapping(target = "guessId", ignore = true)
    @Mapping(target = "isValid", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "round", ignore = true)
    @Mapping(target = "startTime", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "totalTime", ignore = true)
    @Mapping(target = "score", ignore = true)
    Guess convertGuessPostDTOtoGuessEntity(GuessPostDTO guessPostDTO);
    
    @Mapping(source = "guessId", target = "guessId")
    @Mapping(source = "word", target = "word")
    @Mapping(source = "isValid", target = "isValid")
    @Mapping(source = "score", target = "score")
    GuessGetDTO convertGuessEntityToGuessGetDTO(Guess guess);


    @Mapping(source = "selectedWord", target = "selectedWord")
    @Mapping(target = "wordCardId", ignore = true)
    @Mapping(target = "word1", ignore = true)
    @Mapping(target = "word2", ignore = true)
    @Mapping(target = "word3", ignore = true)
    @Mapping(target = "word4", ignore = true)
    @Mapping(target = "word5", ignore = true)

    WordCard convertWordCardPutDTOtoWordCardEntity(WordCardPutDTO wordCardPutDTO);

    @Mapping(source = "word", target = "word")
    @Mapping(target = "clueId", ignore = true)
    @Mapping(target = "isValid", ignore = true)
    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "round", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "ownerId", ignore = true)
    @Mapping(target = "startTime", ignore = true)
    @Mapping(target = "endTime", ignore = true)
    @Mapping(target = "totalTime", ignore = true)
    @Mapping(target = "voteCount", ignore = true)
    @Mapping(target = "score", ignore = true)
    Clue convertCluePostDTOtoClueEntity(CluePostDTO cluePostDTO);

    @Mapping(source = "clueId", target = "clueId")
    @Mapping(source = "word", target = "word")
    @Mapping(source = "isValid", target = "isValid")
    @Mapping(source = "votes", target = "votes")
    @Mapping(source = "voteCount", target = "voteCount")
    @Mapping(source = "ownerId", target = "ownerId")
    @Mapping(source = "totalTime", target = "totalTime")
    @Mapping(source = "score", target = "score")
    ClueGetDTO convertClueEntityToClueGetDTO(Clue clue);

    @Mapping(source = "vote", target = "vote")
    Vote convertVotePutDTOtoVoteEntity(VotePutDTO votePutDTO);

    @Mapping(target = "messageId", ignore = true)
    @Mapping(source = "username",target = "username")
    @Mapping(source = "icon", target = "icon")
    @Mapping(source = "text", target = "text")
    @Mapping(target = "timeCreated", ignore = true)
    @Mapping(target = "game", ignore = true)
    Message convertMessagePostDTOtoMessageEntity(MessagePostDTO messagePostDTO);


    @Mapping(source = "messageId", target = "messageId")
    @Mapping(source = "username",target = "username")
    @Mapping(source = "icon", target = "icon")
    @Mapping(source = "text", target = "text")
    @Mapping(source = "timeCreated", target = "timeCreated")
    MessageGetDTO convertMessageEntityToMessageGetDTO(Message message);



}
