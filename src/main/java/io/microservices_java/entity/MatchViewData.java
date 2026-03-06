package io.microservices_java.entity;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

//должен подготавливать данные для вывода на экран
//в том числе отобржать эти 15/30/40 что то такое
public class MatchViewData {
    UUID matchId;

    public String playerOneName;
    public String playerTwoName;

    public Integer playerOnePoint;
    public Integer playerOneGame;
    public Integer playerOneSet;

    public Integer playerSecondPoint;
    public Integer playerSecondGame;
    public Integer playerSecondSet;



    public MatchViewData(UUID matchId, Integer playerOnePoint, Integer playerOneGame, Integer playerOneSet, Integer playerSecondPoint, Integer playerSecondGame, Integer playerSecondSet, String playerOneName, String playerTwoName) {
        this.matchId = matchId;
        this.playerOnePoint = playerOnePoint;
        this.playerOneGame = playerOneGame;
        this.playerOneSet = playerOneSet;
        this.playerSecondPoint = playerSecondPoint;
        this.playerSecondGame = playerSecondGame;
        this.playerSecondSet = playerSecondSet;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
    }

    public String getPlayerPointView(String player) {

        String result = "";

        Integer playerPoint = player.equals("playerOne") ? playerOnePoint : playerSecondPoint;
        Integer otherPlayerPoint = Objects.equals(playerPoint, playerOnePoint) ? playerSecondPoint : playerOnePoint;
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
                if(playerPoint > otherPlayerPoint) {
                    result = "Лидер";
                }

                if(playerPoint.equals(otherPlayerPoint)) {
                    result = "---";
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


    public static class Builder {
        UUID matchId;

        String playerOneName;
        String playerTwoName;

        Integer playerOnePoint;
        Integer playerOneGame;
        Integer playerOneSet;

        Integer playerSecondPoint;
        Integer playerSecondGame;
        Integer playerSecondSet;

        public Builder playerOneName (String playerOneName) {
            this.playerOneName = playerOneName;
            return this;
        }

        public Builder playerTwoName (String playerTwoName) {
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

        public Builder playerOneSet(Integer playerOneSet) {
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

        public Builder playerSecondSet(Integer playerSecondSet) {
            this.playerSecondSet = playerSecondSet;
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
                    playerTwoName
            );
        }
    }
}