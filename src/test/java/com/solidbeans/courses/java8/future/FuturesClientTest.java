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
        CompletableFuture.anyOf(a, b, c).join();

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
        CompletableFuture.allOf(a, b, c).join();

        Duration elapsed = Duration.between(start, Instant.now());
        log("elapsed = " + elapsed);

        assertThat(elapsed.getSeconds(), is(5L));
    }


    @Test
    public void it_should_do_stuff() throws Exception {
        int ten =
                client.replyAfter(3)
                        .thenApply(i -> i + 5)
                        .thenApply(i -> i + 2)
                        .join();

        assertThat(ten, is(10));
    }

    @Test
    public void it_should_do_some_more_stuff() throws Exception {
        int ten =
                client.replyAfter(3)
                        .thenCompose(i -> client.replyAfter(i + 5))
                        .thenCompose(i -> client.replyAfter(i + 2))
                        .join();

        assertThat(ten, is(10));
    }


    @Test
    public void it_should_do_even_more_stuff() throws Exception {
        CompletableFuture<Integer> three = client.replyAfter(3);
        CompletableFuture<Integer> five = client.replyAfter(5);
        int plus = three.thenCombine(five, (t, f) -> t + f).join();

        assertThat(plus, is(8));
    }



}

