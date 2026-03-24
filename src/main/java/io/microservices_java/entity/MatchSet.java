package io.microservices_java.entity;

public class MatchSet extends MatchComponent {
    public MatchSet(String player1, String player2, int setIndex, String gameSet) {
        super(player1, player2, setIndex,"set" + gameSet);
    }
}
