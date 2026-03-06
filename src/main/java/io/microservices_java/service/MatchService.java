package io.microservices_java.service;

import io.microservices_java.entity.Game;
import io.microservices_java.entity.MatchData;
import io.microservices_java.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

//добавляет, хранит, удаляет матчи???
// будем его использовать через DI
public class MatchService {


    Player playerOne;
    Player playerTwo;

    Map<UUID, MatchData> matchDataMap = new HashMap<>();

    public MatchService() {
        UUID key = UUID.fromString("d8c0de72-67e1-4370-8165-f1852febbf35");
        this.matchDataMap.put(key, new MatchData(key, "alesha", "serega"));
    }

//    private boolean checkGameStatus(UUID uuid, String player){
//
//    }

    public MatchData getMatchData(UUID uuid) {
        return matchDataMap.get(uuid);
    }

    public void startMatch(UUID uuid, String playerOneName, String playerTwoName) {
        this.matchDataMap.put(uuid, new MatchData(uuid, playerOneName, playerTwoName));
        this.playerOne = new Player();
        playerOne.setName(playerOneName);

        this.playerOne = new Player();
        playerTwo.setName(playerTwoName);
    }

    public void updateMatchProgress(UUID uuid, String player) {
        MatchData matchData = this.matchDataMap.get(uuid);
        Game currentGame = matchData.getCurrentGame();

        Integer one = currentGame.getPlayer1Points();
        Integer two = currentGame.getPlayer2Points();

        int max = Math.max(one, two);
        int spread = Math.abs(one - two);

        if (max >= 4 && spread > 1) {
            if (max == one) {
                matchData.updateOnePlayerGameScore();
            } else {
                matchData.updateTwoPlayerGameScore();

            }

            currentGame.finished();
            matchData.addNewGame();
        }
    }

    public void updateScore(UUID uuid, String player) {
        //получаем конкретный матч,

        MatchData matchData = this.matchDataMap.get(uuid);
        matchData.updateScore(player);
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Player getPlayerOne() {
        return playerOne;
    }
}
