package io.microservices_java.service;

public class Service {

    private int count = 0;
    private boolean isDisabled = false;


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
}
