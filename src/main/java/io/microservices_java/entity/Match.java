package io.microservices_java.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "player1_id")
    public Player playerEntity1;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "player2_id")
    public Player playerEntity2;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "winner_id")
    public Player winner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return playerEntity1;
    }

    public void setPlayer1(Player playerEntity1) {
        this.playerEntity1 = playerEntity1;
    }

    public Player getPlayer2() {
        return playerEntity2;
    }

    public void setPlayer2(Player playerEntity2) {
        this.playerEntity2 = playerEntity2;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
