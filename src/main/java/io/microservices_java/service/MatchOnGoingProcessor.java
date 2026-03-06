package io.microservices_java.service;

public class MatchOnGoingProcessor {

    private static final MatchOnGoingProcessor INSTANCE = new MatchOnGoingProcessor();

    private MatchOnGoingProcessor() {
        System.out.println("MatchOnGoingProcessor init");
    }

    public static MatchOnGoingProcessor getInstance() {
        return INSTANCE;
    }

}
