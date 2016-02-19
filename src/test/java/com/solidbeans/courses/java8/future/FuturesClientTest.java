package com.solidbeans.courses.java8.future;

import com.solidbeans.courses.java8.client.FuturesClient;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import static com.solidbeans.courses.java8.Utils.log;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FuturesClientTest {

    private FuturesClient client;

    @Before
    public void setUp() {
        Executor executor = Executors.newFixedThreadPool(10);
        client = new FuturesClient(executor);
    }

    @Test
    public void it_should_call_the_client_and_wait_for_the_first_one_to_finish() {
        Instant start = Instant.now();

        CompletableFuture<Integer> a = client.replyAfter(3);
        CompletableFuture<Integer> b = client.replyAfter(5);
        CompletableFuture<Integer> c = client.replyAfter(2);

        // Add some stuff to make the test pass

        Duration elapsed = Duration.between(start, Instant.now());
        log("elapsed = " + elapsed);

        assertThat(elapsed.getSeconds(), is(2L));
    }

    @Test
    public void it_should_call_the_client_and_wait_for_the_last_one_to_finish() {
        Instant start = Instant.now();

        CompletableFuture<Integer> a = client.replyAfter(3);
        CompletableFuture<Integer> b = client.replyAfter(5);
        CompletableFuture<Integer> c = client.replyAfter(2);

        // Add some stuff to make the test pass

        Duration elapsed = Duration.between(start, Instant.now());
        log("elapsed = " + elapsed);

        assertThat(elapsed.getSeconds(), is(5L));
    }



}

