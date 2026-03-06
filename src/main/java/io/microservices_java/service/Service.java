package io.microservices_java.service;

import io.microservices_java.entity.Game;
import io.microservices_java.entity.Match;

import java.util.UUID;

public class Service {

    private int count = 0;
    private boolean isDisabled = false;


//    public Player getWinner();

    public void inc(){
        isDisabled = true;
        count++;
        isDisabled = false;
    }

    public void dec(){
        isDisabled = true;
        count--;
        isDisabled = false;
    }

    public int getCount(){
        return count;
    }

    public boolean getIsDisabled(){
        return isDisabled;
    }

//    public Game createMatch(String playerOneName, String playerTwoName, UUID gameId){
//        return new Match(playerOneName, playerTwoName, gameId);
//    }

}
