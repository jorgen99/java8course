package com.solidbeans.courses.java8;

public abstract class Utils {

    public static void log(String msg) {
        long millis = System.currentTimeMillis();
        String name = Thread.currentThread().getName();
        System.out.println(String.format("%s : %-12s : %s", millis, name, msg));
    }

    public static void sleep(int seconds) {
        try {
            String msg = "Sleeping for " + seconds + "s...";
            log(msg);
            Thread.sleep(seconds * 1000);
            log("Woke up...");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
