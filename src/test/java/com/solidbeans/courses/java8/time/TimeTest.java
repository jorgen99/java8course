package com.solidbeans.courses.java8.time;

import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TimeTest {


    @BeforeClass
    public static void once() throws Exception {
        Locale.setDefault(new Locale("sv", "SE"));
    }

    @Test
    public void it_should_calculate_time_difference_in_seconds() throws Exception {
        // Given
        Instant start = Instant.now();

        // When
        Thread.sleep(2000);
        Duration elapsed = Duration.between(start, Instant.now());

        // Then
        assertThat(elapsed.getSeconds(), is(2L));
    }

    @Test
    public void it_should_format_a_date_accordning_to_default_locale() throws Exception {
        // Given
        LocalDateTime now = LocalDateTime.now();

        // When
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        String fomattedDate = formatter.format(now);

        int year = now.get(ChronoField.YEAR);
        int month = now.get(ChronoField.MONTH_OF_YEAR);
        int day = now.get(ChronoField.DAY_OF_MONTH);
        String isoDate = String.format("%4d-%02d-%02d", year, month, day);

        // Then
        assertThat(fomattedDate, is(equalTo(isoDate)));
    }
}

