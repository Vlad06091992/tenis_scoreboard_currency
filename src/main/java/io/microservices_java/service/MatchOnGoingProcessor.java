package io.microservices_java.service;

import io.microservices_java.entity.Game;
import io.microservices_java.entity.MatchData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//добавляет, хранит, управляет состоянием матчей
public class MatchOnGoingProcessor {
    Map<UUID, MatchData> matchDataMap = new HashMap<>();

    public MatchOnGoingProcessor() {
        UUID key = UUID.fromString("d8c0de72-67e1-4370-8165-f1852febbf35");
        this.matchDataMap.put(key, new MatchData(key, "alesha", "serega"));
    }


    public MatchData getMatchData(UUID uuid) {
        return matchDataMap.get(uuid);
    }

    public void startMatch(UUID uuid, String playerOneName, String playerTwoName) {
        this.matchDataMap.put(uuid, new MatchData(uuid, playerOneName, playerTwoName));
    }

    // завершает текущую игру, создаёт новую, если выполнены условия
    public void updateGameProgress(MatchData matchData) {
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

    // обновляет очки в текущей игре
    public void updateScore(MatchData matchData, String player) {
        Game currentGame = matchData.getCurrentGame();

        if (player.equals("playerOne")) {
            currentGame.setPlayer1Points(currentGame.getPlayer1Points() + 1);
        }
        if (player.equals("playerTwo")) {
            currentGame.setPlayer2Points(currentGame.getPlayer2Points() + 1);
        }
    }

    //управляет состояние матча, обновляет очки в игре, обновляет игры, обновляет сеты, завершает матч
    public void updateMatchProgress(UUID matchId, String player) {
        MatchData matchData = this.matchDataMap.get(matchId);
        updateScore(matchData, player);
        updateGameProgress(matchData);

    }
}
