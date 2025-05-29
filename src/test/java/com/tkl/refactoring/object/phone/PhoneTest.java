package com.tkl.refactoring.object.phone;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;

class PhoneTest {
	@Test
	void test() {
		Phone phone = new Phone(Money.wons(1000), Duration.ofSeconds(10));
		NightlyDiscountPhone nightlyDiscountPhone = new NightlyDiscountPhone(Money.wons(2000), Money.wons(1000), Duration.ofSeconds(10));

		Call call = new Call(LocalDateTime.of(2023, 10, 1, 22, 0)
				, LocalDateTime.of(2023, 10, 1, 23, 0));
		System.out.println(phone.calculateFee(call));
		System.out.println(nightlyDiscountPhone.calculateFee(call));
	}
}