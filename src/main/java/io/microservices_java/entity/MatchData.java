package io.microservices_java.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

//хранилище данных матча с тупыми методами для обновления состояния
// берет матч и преобразует данные для вывода в jsp
// у матча должно быть состояние, он должен сам, или с помощью хелпера менять свое состояние
// в течении игры
public class MatchData {

    String player1 = "";
    String player2 = "";
    int playerOneGameScore = 0;
    int playerTwoGameScore = 0;

    ArrayList<Game> games = new ArrayList<>();
    UUID matchId;

    public MatchData(UUID matchId, String playerOneName, String playerTwoName) {
        this.matchId = matchId;
        this.player1 = playerOneName;
        this.player2 = playerTwoName;
        games.add(new Game(playerOneName, playerTwoName, 0));

    }


    public Game getCurrentGame() {
        Collections.sort(games, Comparator.comparing(Game::getGameIndex));
        return games.get(games.size() - 1);
    }


    public void addNewGame() {
        int index = games.size();
        games.add(new Game(player1, player2, index));
    }

    public void updateOnePlayerGameScore() {
        playerOneGameScore = playerOneGameScore + 1;
    }

    public void updateTwoPlayerGameScore() {
        playerTwoGameScore = playerTwoGameScore + 1;
    }

    public int getPlayerTwoGameScore() {
        return playerTwoGameScore;
    }

    public int getPlayerOneGameScore() {
        return playerOneGameScore;
    }

    public MatchViewData getMatchViewData() {

        Game currentGame = getCurrentGame();

        Integer player1Points = currentGame.getPlayer1Points();
        Integer player2Points = currentGame.getPlayer2Points();


        return new MatchViewData
                .Builder()
                .matchId(UUID.fromString(matchId.toString()))
                .playerOneName(currentGame.player1)
                .playerTwoName(currentGame.player2)
                .playerOneSet(0)
                .playerOneGame(getPlayerOneGameScore())
                .playerOnePoint(player1Points)
                .playerSecondSet(0)
                .playerSecondGame(getPlayerTwoGameScore())
                .playerSecondPoint(player2Points)
                .build();
    }

}
