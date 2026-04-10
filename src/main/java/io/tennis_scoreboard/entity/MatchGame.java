package io.tennis_scoreboard.entity;

public class MatchGame extends MatchComponent {
    public MatchGame(String player1, String player2, int gameIndex, String gameName ) {
        super(player1, player2, gameIndex, "game" + gameName);
    }
}
