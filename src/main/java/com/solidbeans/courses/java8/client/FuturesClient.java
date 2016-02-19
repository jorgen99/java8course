package com.solidbeans.courses.java8.client;

import com.solidbeans.courses.java8.Utils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

public class FuturesClient {

    private final Executor executor;

    public FuturesClient(Executor executor) {
        this.executor = executor;
    }

    public CompletableFuture<Integer> replyAfter(final int seconds) {
        return CompletableFuture.supplyAsync(() -> {
            Utils.sleep(seconds);
            return seconds;
        }, executor);
    }

}
