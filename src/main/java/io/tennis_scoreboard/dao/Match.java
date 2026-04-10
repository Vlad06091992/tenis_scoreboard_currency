package io.tennis_scoreboard.dao;

import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "Match")
@Table(name = "matches")
public class Match {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "winner")
    private String winner;

    @Column(name = "playerOne")
    private String playerOne;

    @Column(name = "playerRwo")
    private String playerTwo;

    public Match() {}

    public String getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id.toString();
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }
}
