package com.solidbeans.courses.java8.stream;

import com.solidbeans.courses.java8.Utils;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static com.solidbeans.courses.java8.Utils.log;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class StreamTest {


    @Test
    public void parallel_stream_will_run_out_of_threads_if_we_have_many_items() throws Exception {
        // This test will fail if you have many cores... :)

        Instant start = Instant.now();
        List<Integer> seconds = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1);

        seconds.parallelStream().forEach(Utils::sleep);

        Duration elapsed = Duration.between(start, Instant.now());
        log(seconds.size() + " items, elapsed: " + elapsed.getSeconds() + "s");

        assertThat(elapsed.getSeconds(), is(not(1L)));
    }
}
