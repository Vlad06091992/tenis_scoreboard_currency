package io.tennis_scoreboard.entity;

public class MatchTieBreak extends MatchGame {
    public MatchTieBreak(String player1, String player2, int gameIndex, String gameName) {
        super(player1, player2, gameIndex, "tai-break" + gameName);
    }
}
