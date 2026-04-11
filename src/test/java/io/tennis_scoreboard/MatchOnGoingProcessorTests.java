package io.tennis_scoreboard;

import io.tennis_scoreboard.entity.MatchData;
import io.tennis_scoreboard.entity.MatchGame;
import io.tennis_scoreboard.entity.MatchTieBreak;
import io.tennis_scoreboard.service.MatchOnGoingProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MatchOnGoingProcessorTests {
    static class TestUtils {
        static public void updateMatchProgressTestMethod(
                MatchOnGoingProcessor matchOnGoingProcessor,
                MatchData matchData,
                String player,
                int count) {

            for (int i = 0; i < count; i++) {
                matchOnGoingProcessor.updateMatchProgress(matchData.getMatchId(), player);
            }
        }
    }



    private MatchOnGoingProcessor matchOnGoingProcessor;
    UUID[] matchIds = {
            UUID.fromString("5bc27741-e936-4a00-907a-046b17f2aa75"),
            UUID.fromString("f282290b-3789-40a7-be44-9b3aceb4a412")
    };

    @BeforeEach
    void setUp() {
        matchOnGoingProcessor = new MatchOnGoingProcessor();
        matchOnGoingProcessor.startMatch(matchIds[0], "Valera", "Vlad");
        matchOnGoingProcessor.startMatch(matchIds[1], "Andrey", "Volodya");

    }

    @Test
    void testMatchDataMap() {
        assertEquals(2, matchOnGoingProcessor.getMatchDataMap().size());
        assertEquals("Valera", matchOnGoingProcessor.getMatchData(UUID.fromString("5bc27741-e936-4a00-907a-046b17f2aa75")).getPlayer1());
        assertEquals("Vlad", matchOnGoingProcessor.getMatchData(UUID.fromString("5bc27741-e936-4a00-907a-046b17f2aa75")).getPlayer2());
    }

    @Test
    void updateProgressGamesAndCounts() {
        MatchData matchData = matchOnGoingProcessor.getMatchData(matchIds[0]);

        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",5);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",3);

        Integer gamesFirstPlayer = matchData.getCurrentSet().getPlayer1Points();
        Integer gamesSecondPlayer = matchData.getCurrentSet().getPlayer2Points();

        Integer scoreFirstPlayer = matchData.getCurrentGame().getPlayer1Points();
        Integer scoreSecondPlayer = matchData.getCurrentGame().getPlayer2Points();


        assertEquals(1, gamesFirstPlayer);
        assertEquals(0, gamesSecondPlayer);

        assertEquals(1, scoreFirstPlayer);
        assertEquals(3, scoreSecondPlayer);
    }

    @Test
    void taiBreakCase() {
        MatchData matchData = matchOnGoingProcessor.getMatchData(matchIds[0]);

        //играем до 6 побед каждому участнику, чтобы начался тай брейк
        // 1 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",4);
        // 1 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",4);

        // 2 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",4);
        // 2 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",4);

        // 3 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",4);
        // 3 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",4);

        // 4 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",4);
        // 4 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",4);

        // 5 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",4);
        // 5 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",4);

        // 6 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",4);
        // 6 гейм, победил первый игрок
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",4);

        Integer gamesFirstPlayer = matchData.getCurrentSet().getPlayer1Points();
        Integer gamesSecondPlayer = matchData.getCurrentSet().getPlayer2Points();
        MatchGame matchGame = matchData.getCurrentGame();

        assertEquals(6, gamesFirstPlayer);
        assertEquals(6, gamesSecondPlayer);
        assertInstanceOf(MatchTieBreak.class, matchGame);
    }



    @Test
    void winFirstPlayerInGame() {
        MatchData matchData = matchOnGoingProcessor.getMatchData(matchIds[0]);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",3);
        int points = matchData.getCurrentGame().getPlayer1Points();
        assertEquals(3, points);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",1);
        int pointsInNewGame = matchData.getCurrentGame().getPlayer1Points();
        assertEquals(0, pointsInNewGame);
    }


    @Test
    void winSecondPlayerInGame() {
        MatchData matchData = matchOnGoingProcessor.getMatchData(matchIds[0]);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",3);
        int points = matchData.getCurrentGame().getPlayer1Points();
        assertEquals(3, points);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",1);
        int pointsInNewGame = matchData.getCurrentGame().getPlayer1Points();
        assertEquals(0, pointsInNewGame);
    }

    @Test
    void winFirstPlayerInSet() {
        MatchData matchData = matchOnGoingProcessor.getMatchData(matchIds[0]);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",23);
        int points = matchData.getPlayerOneSetScore();
        assertEquals(0, points);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",1);
        int pointsAfterWinInSet = matchData.getPlayerOneSetScore();
        assertEquals(1, pointsAfterWinInSet);
    }

    @Test
    void winSecondPlayerInSet() {
        MatchData matchData = matchOnGoingProcessor.getMatchData(matchIds[0]);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",23);
        int points = matchData.getPlayerTwoSetScore();
        assertEquals(0, points);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",1);
        int pointsAfterWinInSet = matchData.getPlayerTwoSetScore();
        assertEquals(1, pointsAfterWinInSet);
    }

    @Test
    void winSecondPlayerInMatch() {
        MatchData matchData = matchOnGoingProcessor.getMatchData(matchIds[0]);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerTwo",48);
        int secondPlayerSetsCount = matchData.getCurrentGame().getPlayer2Points();

        assertTrue(matchData.getIsFinished());
        assertEquals(0, secondPlayerSetsCount);
    }

    @Test
    void winFirstPlayerInMatch() {
        MatchData matchData = matchOnGoingProcessor.getMatchData(matchIds[0]);
        TestUtils.updateMatchProgressTestMethod(matchOnGoingProcessor,matchData,"playerOne",48);
        int secondPlayerSetsCount = matchData.getCurrentGame().getPlayer2Points();

        assertTrue(matchData.getIsFinished());
        assertEquals(0, secondPlayerSetsCount);
    }
}