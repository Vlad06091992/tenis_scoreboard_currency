package io.microservices_java.entity;

import java.util.UUID;

public class Game {
    Boolean isFinished;
    String winner = null;
    String player1;
    String player2;
    Integer player1Points = 0;
    Integer player2Points = 0;
    Integer gameIndex;

    public Game(String player1, String player2, int gameIndex ) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameIndex = gameIndex;
    }

    public Integer getPlayer1Points() {
        return player1Points;
    }

    public void setPlayer1Points(Integer player1Points) {
        this.player1Points = player1Points;
    }

    public Integer getPlayer2Points() {
        return player2Points;
    }

    public void setPlayer2Points(Integer player2Points) {
        this.player2Points = player2Points;
    }

    public void finished(){
        isFinished = true;
//        winner
    }

    public Integer getGameIndex() {
        return gameIndex;
    }
}
