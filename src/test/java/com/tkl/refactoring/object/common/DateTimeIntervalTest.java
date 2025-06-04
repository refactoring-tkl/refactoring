package com.tkl.refactoring.object.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DateTimeIntervalTest {
	@Test
	void test() {
		DateTimeInterval dateTimeInterval = DateTimeInterval.of(LocalDateTime.of(2023, 10, 1, 0, 0, 0),
																LocalDateTime.of(2023, 10, 5, 23, 59, 59));

		assertThat(dateTimeInterval.splitByDay()).hasSize(5);
	}
}