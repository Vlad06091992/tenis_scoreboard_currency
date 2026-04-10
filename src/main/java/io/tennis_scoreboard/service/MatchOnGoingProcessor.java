package io.tennis_scoreboard.service;

import io.tennis_scoreboard.entity.MatchGame;
import io.tennis_scoreboard.entity.MatchData;
import io.tennis_scoreboard.entity.MatchSet;
import io.tennis_scoreboard.entity.MatchTieBreak;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchOnGoingProcessor {
    Map<UUID, MatchData> matchDataMap = new HashMap<>();

    public MatchOnGoingProcessor() {
//        UUID key = UUID.fromString("d8c0de72-67e1-4370-8165-f1852febbf35");
//        this.matchDataMap.put(key, new MatchData(key, "alesha", "serega"));
    }

    public Map<UUID, MatchData> getMatchDataMap() {
        return matchDataMap;
    }

    public MatchData getMatchData(UUID uuid) {
        return matchDataMap.get(uuid);
    }

    public void startMatch(UUID uuid, String playerOneName, String playerTwoName) {
        this.matchDataMap.put(uuid, new MatchData(uuid, playerOneName, playerTwoName));
    }

    private void updateScoreInGame(MatchData matchData, String player) {
        MatchGame currentGame = matchData.getCurrentGame();

        if (player.equals("playerOne")) {
            currentGame.setPlayer1Points(currentGame.getPlayer1Points() + 1);
        }
        if (player.equals("playerTwo")) {
            currentGame.setPlayer2Points(currentGame.getPlayer2Points() + 1);
        }

        MatchSet currentSet = matchData.getCurrentSet();
        String winner;
        Integer one = currentGame.getPlayer1Points();
        Integer two = currentGame.getPlayer2Points();

        boolean isTaiBreak = currentGame instanceof MatchTieBreak;


        int max = Math.max(one, two);
        int spread = Math.abs(one - two);


        //в рамках сета стартуем новую игру
        if (isTaiBreak ?  (max >= 7 && spread > 1): (max >= 4 && spread > 1) ) {
            if (max == one) {
                winner = matchData.getPlayer1();
                currentSet.setPlayer1Points(currentSet.getPlayer1Points() + 1);
            } else {
                winner = matchData.getPlayer2();
                currentSet.setPlayer2Points(currentSet.getPlayer2Points() + 1);

            }
            currentGame.finished(winner);

            if(!isTaiBreak) {
                int firstPlayerWinsGamesCount = currentSet.getPlayer1Points();
                int secondPlayerWinsGamesCount = currentSet.getPlayer2Points();

                if (firstPlayerWinsGamesCount == 6 && secondPlayerWinsGamesCount == 6) {
                    matchData.addTieBreak();
                } else {
                    matchData.addNewGame();
                }
            }

        }

    }

    private void updateGamesInSet(MatchData matchData) {
        MatchSet currentSet = matchData.getCurrentSet();
        MatchGame currentGame = matchData.getCurrentGame();
        Integer one = currentSet.getPlayer1Points();
        Integer two = currentSet.getPlayer2Points();

        boolean isTaiBreak = currentGame instanceof MatchTieBreak;
        int max = Math.max(one, two);
        int spread = Math.abs(one - two);


        if ( (isTaiBreak && currentGame.getFinished()) ||  (max >= 6 && spread > 1)) {
            String winner;
            if (max == one) {
                winner = matchData.getPlayer1();
                currentSet.setPlayer1Points(currentSet.getPlayer1Points() + 1);
                matchData.updateOnePlayerSetScore();
            } else {
                winner = matchData.getPlayer2();
                matchData.updateTwoPlayerSetScore();
                currentSet.setPlayer2Points(currentSet.getPlayer2Points() + 1);
            }
            matchData.getCurrentSet().finished(winner);
            matchData.addNewSet();
            if(isTaiBreak) {
                matchData.addNewGame();
            }
        }
    }

    private void updateMatchStatus(MatchData matchData){
        int frst = matchData.getPlayerOneSetScore();
        int second = matchData.getPlayerTwoSetScore();

        int spread = Math.abs(frst - second);
        int max = Math.max(frst, second);

        if (max > 1 && spread > 0) {
            if (max == frst) {
                matchData.setFinished(matchData.getPlayer1());
            } else {
                matchData.setFinished(matchData.getPlayer2());
            }
        }

    }


    public void updateMatchProgress(UUID matchId, String player) {
        MatchData matchData = this.matchDataMap.get(matchId);
        updateScoreInGame(matchData, player);
        updateGamesInSet(matchData);
        updateMatchStatus(matchData);

    }
}
