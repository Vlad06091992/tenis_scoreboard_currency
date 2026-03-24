package io.microservices_java.entity;

public abstract class MatchComponent {
    String name;
    Boolean isFinished = false;

    public String getWinner() {
        return winner;
    }

    String winner = null;
    String player1;
    String player2;
    Integer player1Points = 0;
    Integer player2Points = 0;
    Integer index;

    public MatchComponent(String player1, String player2, int index,String name ) {
        this.player1 = player1;
        this.player2 = player2;
        this.index = index;
        this.name = name;
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

    public void finished(String player){
        isFinished = true;
        winner = player;
    }

    public Integer getIndex() {
        return index;
    }

    public Boolean getFinished() {
        return isFinished;
    }
}
