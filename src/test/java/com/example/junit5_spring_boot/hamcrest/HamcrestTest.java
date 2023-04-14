package com.example.junit5_spring_boot.hamcrest;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;

public class HamcrestTest {

    @Test
    public void hamcrestTest() {
        List<Integer> numbers = List.of(5, 14, 45);

        assertThat(numbers, hasSize(3));
        assertThat(numbers, hasItems(5, 45));
        assertThat(numbers, everyItem(greaterThan(2)));
        assertThat(numbers, everyItem(lessThan(100)));

        assertThat("", isEmptyString());
        assertThat("ABCDE", containsString("BCD"));
        assertThat("ABCDE", startsWith("ABC"));
        assertThat("ABCDE", endsWith("CDE"));
    }
}
