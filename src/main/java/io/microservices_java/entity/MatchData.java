package io.microservices_java.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.UUID;

public class MatchData {
    private boolean isFinished = false;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    private String winner;
    private String player1 = "";
    private String player2 = "";

    private int playerOneSetScore = 0;
    private int playerTwoSetScore = 0;

    private ArrayList<MatchGame> matchGames = new ArrayList<>();
    private ArrayList<MatchSet> matchSets = new ArrayList<>();


    public MatchData(UUID matchId, String playerOneName, String playerTwoName) {
        this.matchId = matchId;
        this.player1 = playerOneName;
        this.player2 = playerTwoName;
        matchGames.add(new MatchGame(playerOneName, playerTwoName, 0, String.valueOf(0)));
        matchSets.add(new MatchSet(playerOneName, playerTwoName, 0, String.valueOf(0)));

    }

    public UUID getMatchId() {
        return matchId;
    }

    private UUID matchId;

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public MatchGame getCurrentGame() {
        matchGames.sort(Comparator.comparing(MatchGame::getIndex));
        return matchGames.get(matchGames.size() - 1);
    }

    public MatchSet getCurrentSet() {
        matchSets.sort(Comparator.comparing(MatchSet::getIndex));
        return matchSets.get(matchSets.size() - 1);
    }


    public void addNewGame() {
        int index = matchGames.size();
        matchGames.add(new MatchGame(player1, player2, index, String.valueOf(index)));
    }

    public void addTieBreak() {
        int index = matchGames.size();
        matchGames.add(new MatchTieBreak(player1, player2, index, String.valueOf(index)));
    }

    public void addNewSet() {
        int index = matchSets.size();
        matchSets.add(new MatchSet(player1, player2, index, String.valueOf(index)));
    }

    public void updateOnePlayerSetScore() {
        playerOneSetScore = playerOneSetScore + 1;
    }

    public void updateTwoPlayerSetScore() {
        playerTwoSetScore = playerTwoSetScore + 1;
    }

    public int getPlayerTwoGameScore() {
        MatchSet currentMatchSet = getCurrentSet();
        Integer player2Points = currentMatchSet.getPlayer2Points();
        return player2Points;
    }

    public int getPlayerOneGameScore() {
        MatchSet currentMatchSet = getCurrentSet();
        Integer player1Points = currentMatchSet.getPlayer1Points();
        return player1Points;
    }

    public int getPlayerTwoSetScore() {
        return playerTwoSetScore;
    }

    public int getPlayerOneSetScore() {
        return playerOneSetScore;
    }

    public MatchViewData getMatchViewData() {

        MatchGame currentMatchGame = getCurrentGame();

        Integer player1Points = currentMatchGame.getPlayer1Points();
        Integer player2Points = currentMatchGame.getPlayer2Points();
        Boolean isTaiBreak = currentMatchGame instanceof MatchTieBreak;

        return new MatchViewData
                .Builder()
                .matchId(UUID.fromString(matchId.toString()))
                .firstPlayerName(currentMatchGame.player1)
                .secondPlayerName(currentMatchGame.player2)
                .firstPlayerSetCount(getPlayerOneSetScore())
                .secondPlayerSetCount(getPlayerTwoSetScore())



                .playerOneGame(getPlayerOneGameScore())
                .playerOnePoint(player1Points)
                .playerSecondGame(getPlayerTwoGameScore())
                .playerSecondPoint(player2Points)
                .setIsTaiBreak(isTaiBreak)
                .setIsFinished(getIsFinished())
                .setWinner(getWinner())
                .build();
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setFinished(String winner) {
        isFinished = true;
        this.winner = winner;
    }
}
