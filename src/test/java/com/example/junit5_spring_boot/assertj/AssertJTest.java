package com.example.junit5_spring_boot.assertj;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {

    @Test
    public void assertJTest() {
        List<Integer> numbers = List.of(5, 14, 45);

        assertThat(numbers).hasSize(3)
                .contains(5, 14)
                .allMatch(x -> x > 4)
                .allMatch(x -> x < 100)
                .noneMatch(x -> x < 0);

        assertThat("").isEmpty();

        assertThat("ABCDE").contains("BCD")
                .startsWith("ABC")
                .endsWith("CDE");
    }
}
