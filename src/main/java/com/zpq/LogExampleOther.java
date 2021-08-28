package com.zpq;

public class LogExampleOther {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExampleOther.class);

    public static void main(String... args) {
        log.error("Something else is wrong here");
    }
}
