package io.microservices_java.service;

public class MatchOnGoingProcessorT {

    private static final MatchOnGoingProcessorT INSTANCE = new MatchOnGoingProcessorT();

    private MatchOnGoingProcessorT() {
        System.out.println("MatchOnGoingProcessor init");
    }

    public static MatchOnGoingProcessorT getInstance() {
        return INSTANCE;
    }

}
