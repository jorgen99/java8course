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

        CompletableFuture.allOf(a, b, c).join();

        Duration elapsed = Duration.between(start, Instant.now());
        log("elapsed = " + elapsed);

        assertThat(elapsed.getSeconds(), is(5L));
    }

    @Test
    public void if_we_are_not_specifying_an_executor_we_will_have_no_of_cores_minus_one_threads() throws Exception {
        Instant start = Instant.now();

        FuturesClient cli = new FuturesClient(ForkJoinPool.commonPool());
        CompletableFuture<Integer> a = cli.replyAfter(5);
        CompletableFuture<Integer> b = cli.replyAfter(5);
        CompletableFuture<Integer> c = cli.replyAfter(5);
        CompletableFuture<Integer> d = cli.replyAfter(5);
        CompletableFuture<Integer> e = cli.replyAfter(5);
        CompletableFuture<Integer> f = cli.replyAfter(5);
        CompletableFuture<Integer> g = cli.replyAfter(5);
        CompletableFuture<Integer> h = cli.replyAfter(5);
        CompletableFuture<Integer> i = cli.replyAfter(5);
        CompletableFuture<Integer> j = cli.replyAfter(5);
        CompletableFuture<Integer> k = cli.replyAfter(5);

        CompletableFuture.allOf(a, b, c, d, e, f, g, h, i, j, k).join();

        Duration elapsed = Duration.between(start, Instant.now());
        log("elapsed = " + elapsed);

        assertThat(elapsed.getSeconds(), is(10L));
    }

    @Test
    public void if_we_are_specifying_an_executor_we_will_have_that_many_threads() throws Exception {
        Instant start = Instant.now();

        FuturesClient cli = new FuturesClient(Executors.newFixedThreadPool(11));
        CompletableFuture<Integer> a = cli.replyAfter(5);
        CompletableFuture<Integer> b = cli.replyAfter(5);
        CompletableFuture<Integer> c = cli.replyAfter(5);
        CompletableFuture<Integer> d = cli.replyAfter(5);
        CompletableFuture<Integer> e = cli.replyAfter(5);
        CompletableFuture<Integer> f = cli.replyAfter(5);
        CompletableFuture<Integer> g = cli.replyAfter(5);
        CompletableFuture<Integer> h = cli.replyAfter(5);
        CompletableFuture<Integer> i = cli.replyAfter(5);
        CompletableFuture<Integer> j = cli.replyAfter(5);
        CompletableFuture<Integer> k = cli.replyAfter(5);

        CompletableFuture.allOf(a, b, c, d, e, f, g, h, i, j, k).join();

        Duration elapsed = Duration.between(start, Instant.now());
        log("elapsed = " + elapsed);

        assertThat(elapsed.getSeconds(), is(5L));
    }


}

