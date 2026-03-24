package io.microservices_java.entity;

import java.util.Objects;
import java.util.UUID;

public class MatchViewData {
    private UUID matchId;
    private Boolean isTaiBreak = false;
    private Boolean isFinished = false;
    private String winner;
    private String playerOneName;
    private String playerTwoName;
    private Integer playerOnePoint;
    private Integer playerOneGame;
    private Integer playerOneSet;
    private Integer playerSecondPoint;
    private Integer playerSecondGame;
    private Integer playerSecondSet;

    public MatchViewData(UUID matchId) {
        this.matchId = matchId;
    }

    public MatchViewData(UUID matchId, Integer playerOnePoint, Integer playerOneGame, Integer playerOneSet, Integer playerSecondPoint, Integer playerSecondGame, Integer playerSecondSet, String playerOneName, String playerTwoName, Boolean isTaiBreak, String winner,Boolean isFinished) {
        this.matchId = matchId;
        this.playerOnePoint = playerOnePoint;
        this.playerOneGame = playerOneGame;
        this.playerOneSet = playerOneSet;
        this.playerSecondPoint = playerSecondPoint;
        this.playerSecondGame = playerSecondGame;
        this.playerSecondSet = playerSecondSet;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.isTaiBreak = isTaiBreak;
        this.winner = winner;
        this.isFinished = isFinished;
    }

    public String getPlayerPointView(String player) {
        String result = "";

        Integer playerPoint = player.equals("playerOne") ? playerOnePoint : playerSecondPoint;
        Integer otherPlayerPoint = Objects.equals(playerPoint, playerOnePoint) ? playerSecondPoint : playerOnePoint;

        if (isTaiBreak) {
            return playerPoint.toString();
        }

        switch (playerPoint) {
            case 0:
                result = "0";
                break;
            case 1:
                result = "15";
                break;
            case 2:
                result = "30";
                break;
            case 3:
                result = "40";
                break;
            default:
                if (playerPoint > otherPlayerPoint) {
                    result = "Лидер";
                }

                if (playerPoint.equals(otherPlayerPoint)) {
                    result = "Deuce";
                }
        }
        return result;
    }


    public Integer getPlayerOneGame() {
        return playerOneGame;
    }

    public Integer getPlayerOneSet() {
        return playerOneSet;
    }


    public String getPlayerSecondPoint() {
        return getPlayerPointView("playerSecond");
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public String getPlayerOnePoint() {
        return getPlayerPointView("playerOne");
    }


    public Integer getPlayerSecondGame() {
        return playerSecondGame;
    }


    public Integer getPlayerSecondSet() {
        return playerSecondSet;
    }


    public String getWinner() {
        return winner;
    }

    public Boolean getTaiBreak() {
        return isTaiBreak;
    }

    public Boolean getFinished() {
        return isFinished;
    }


    public static class Builder {
        UUID matchId;
        String winner;
        Boolean isTaiBreak;
        Boolean isFinished;
        String playerOneName;
        String playerTwoName;

        Integer playerOnePoint;
        Integer playerOneGame;
        Integer playerOneSet;

        Integer playerSecondPoint;
        Integer playerSecondGame;
        Integer playerSecondSet;

        public Builder firstPlayerName(String playerOneName) {
            this.playerOneName = playerOneName;
            return this;
        }

        public Builder secondPlayerName(String playerTwoName) {
            this.playerTwoName = playerTwoName;
            return this;
        }

        public Builder playerOnePoint(Integer playerOnePoint) {
            this.playerOnePoint = playerOnePoint;
            return this;
        }

        public Builder matchId(UUID matchId) {
            this.matchId = matchId;
            return this;
        }

        public Builder playerOneGame(Integer playerOneGame) {
            this.playerOneGame = playerOneGame;
            return this;
        }

        public Builder firstPlayerSetCount(Integer playerOneSet) {
            this.playerOneSet = playerOneSet;
            return this;
        }

        public Builder playerSecondPoint(Integer playerSecondPoint) {
            this.playerSecondPoint = playerSecondPoint;
            return this;
        }

        public Builder playerSecondGame(Integer playerSecondGame) {
            this.playerSecondGame = playerSecondGame;
            return this;
        }

        public Builder secondPlayerSetCount(Integer playerSecondSet) {
            this.playerSecondSet = playerSecondSet;
            return this;
        }

        public Builder setIsTaiBreak(Boolean isTaiBreak) {
            this.isTaiBreak = isTaiBreak;
            return this;
        }

        public Builder setIsFinished(Boolean isFinished) {
            this.isFinished = isFinished;
            return this;
        }

        public Builder setWinner(String winner) {
            this.winner = winner;
            return this;
        }

        public MatchViewData build() {
            return new MatchViewData(
                    matchId,
                    playerOnePoint,
                    playerOneGame,
                    playerOneSet,
                    playerSecondPoint,
                    playerSecondGame,
                    playerSecondSet,
                    playerOneName,
                    playerTwoName,
                    isTaiBreak,
                    winner,
                    isFinished
            );
        }
    }
}