package com.tkl.refactoring.object.policy.basic;

import java.time.DayOfWeek;
import java.time.Duration;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.DateTimeInterval;
import com.tkl.refactoring.object.common.Money;

public class DayOfWeekDiscountPolicy extends BasicRatePolicy {
	private final Money holidayAmount;
	private final Money weekdayAmount;
	private final Duration seconds;

	public DayOfWeekDiscountPolicy(Money holidayAmount, Money weekdayAmount, Duration seconds) {
		this.holidayAmount = holidayAmount;
		this.weekdayAmount = weekdayAmount;
		this.seconds = seconds;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		Money result = Money.ZERO;
		for (DateTimeInterval dateTimeInterval : call.splitByDay()) {
			if (isHoliday(dateTimeInterval)) {
				result = result.plus(holidayAmount.times(dateTimeInterval.duration().getSeconds() / seconds.getSeconds()));
			} else {
				result = result.plus(weekdayAmount.times(dateTimeInterval.duration().getSeconds() / seconds.getSeconds()));
			}
		}
		return result;
	}

	private static boolean isHoliday(DateTimeInterval dateTimeInterval) {
		return dateTimeInterval.getTo().getDayOfWeek() == DayOfWeek.SATURDAY || dateTimeInterval.getTo().getDayOfWeek() == DayOfWeek.SUNDAY;
	}
}
