package io.microservices_java.entity;

import io.microservices_java.service.MatchOnGoingProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

//хранилище игр в матче с методами управления их жизненным циклом
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
//        MatchOnGoingProcessor matchOnGoingProcessor = MatchOnGoingProcessor.getInstance();
    }


    public Game getCurrentGame() {
        Collections.sort(games, Comparator.comparing(Game::getGameIndex));
        return games.get(games.size() - 1);
    }


    public void addNewGame() {
        int index = games.size();
        games.add(new Game(player1, player2, index));
    }

    public void updateScore(String player) {
        Game game = getCurrentGame();
        //получили игру, и теперь надо докинуть и очки, и правильно их отобразить
        // на этом этапе должна выполнять логика увеличения очков
        // логика завершения игры, сета, матча

        if (player.equals("playerOne")) {
            game.setPlayer1Points(game.getPlayer1Points() + 1);
        }
        if (player.equals("playerTwo")) {
            game.setPlayer2Points(game.getPlayer2Points() + 1);
        }
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
        Integer player1Points = getCurrentGame().getPlayer1Points();
        Integer player2Points = getCurrentGame().getPlayer2Points();


        return new MatchViewData
                .Builder()
                .matchId(UUID.fromString(matchId.toString()))
                .playerOneSet(5)
                .playerOneGame(getPlayerOneGameScore())
                .playerOnePoint(player1Points)
                .playerSecondSet(9)
                .playerSecondGame(getPlayerTwoGameScore())
                .playerSecondPoint(player2Points)
                .build();
    }

}
