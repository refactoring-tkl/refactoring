package com.tkl.refactoring.object.phone;

import java.util.function.Predicate;

import com.tkl.refactoring.object.common.Money;

public class RegularAmountDiscountPolicy implements ExtraPolicy {
	@Override
	public Money apply(NightlyDiscountPhone nightlyDiscountPhone, Money currentFee) {
		return nightlyDiscountPhone.getCalls().stream()
								   .filter(Predicate.not(nightlyDiscountPhone::isLateNightHour))
								   .map(nightlyDiscountPhone::calculateFee)
								   .map(m -> m.times(0.2))
								   .reduce(currentFee, Money::minus);
	}

	@Override
	public Money apply(Phone phone, Money currentFee) {
		return phone.getCalls().stream()
					.map(phone::calculateFee)
					.map(m -> m.times(0.3))
					.reduce(currentFee, Money::minus);
	}
}
