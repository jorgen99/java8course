package com.solidbeans.courses.java8.client;

import com.solidbeans.courses.java8.Utils;
import com.solidbeans.courses.java8.db.UserDao;
import com.solidbeans.courses.java8.dto.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

public class FuturesClient {

    private final Executor executor;
    private final UserDao userDao = new UserDao();
    private static final User NO_USER = new User(-1, "Not Found");

    public FuturesClient(Executor executor) {
        this.executor = executor;
    }

    public CompletableFuture<Integer> replyAfter(final int seconds) {
        return CompletableFuture.supplyAsync(() -> {
            Utils.sleep(seconds);
            return seconds;
        }, executor);
    }

    public CompletableFuture<User> findUser(int id) {
        return CompletableFuture.supplyAsync(
            () -> userDao.findById(id).orElse(NO_USER)
        );
    }
    public CompletableFuture<User> findUserWithPossibleError(int id) {
        return CompletableFuture.supplyAsync(
            () -> userDao.findByIdPossibleError(id).orElse(NO_USER)
        );
    }

}
