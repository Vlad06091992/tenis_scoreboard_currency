package io.microservices_java.service;

import io.microservices_java.entity.MatchGame;
import io.microservices_java.entity.MatchData;
import io.microservices_java.entity.MatchSet;
import io.microservices_java.entity.MatchTieBreak;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    private void updateScoreInGame(MatchData matchData) {
        MatchGame currentGame = matchData.getCurrentGame();
        MatchSet currentSet = matchData.getCurrentSet();
        String winner;
        Integer one = currentGame.getPlayer1Points();
        Integer two = currentGame.getPlayer2Points();

        boolean isTaiBreak = currentGame instanceof MatchTieBreak;


        int max = Math.max(one, two);
        int spread = Math.abs(one - two);

        boolean b = isTaiBreak ?  (max >= 7 && spread > 1): (max >= 4 && spread > 1) ;

        //в рамках сета стартуем новую игру
        if (b) {
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

//        if(isTaiBreak && currentGame.getFinished()){
//            String winner = currentGame.getWinner();
//            matchData.getCurrentSet().finished(winner);
//            matchData.addNewSet();
//            matchData.addNewGame();
//            return;
//        }




        int max = Math.max(one, two);
        int spread = Math.abs(one - two);



//        boolean b = isTaiBreak ?  (max >= 67 && spread > 1): (max >= 6 && spread > 1) ;
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

    public void updateMatchStatus(MatchData matchData){
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

    public void updateScore(MatchData matchData, String player) {
        MatchGame currentMatchGame = matchData.getCurrentGame();

        if (player.equals("playerOne")) {
            currentMatchGame.setPlayer1Points(currentMatchGame.getPlayer1Points() + 1);
        }
        if (player.equals("playerTwo")) {
            currentMatchGame.setPlayer2Points(currentMatchGame.getPlayer2Points() + 1);
        }
    }

    public void updateMatchProgress(UUID matchId, String player) {
        MatchData matchData = this.matchDataMap.get(matchId);
        updateScore(matchData, player);
        updateScoreInGame(matchData);
        updateGamesInSet(matchData);
        updateMatchStatus(matchData);

    }
}
