package com.tkl.refactoring.object.policy.basic;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.DateTimeInterval;
import com.tkl.refactoring.object.common.Money;

public class IntervalDiscountPolicy extends BasicRatePolicy{
	private final int firstInterval;
	private final int secondInterval;
	private final Money firstAmount;
	private final Money secondAmount;
	private final Money thirdAmount;

	public IntervalDiscountPolicy(int firstInterval, int secondInterval, Money firstAmount, Money secondAmount, Money thirdAmount) {
		this.firstInterval = firstInterval * 60;
		this.secondInterval = secondInterval * 60;
		this.firstAmount = firstAmount;
		this.secondAmount = secondAmount;
		this.thirdAmount = thirdAmount;
	}

	@Override
	protected Money calculateCallFee(Call call) {
		long totalSeconds = call.getDuration().getSeconds();

		// 1. 첫 번째 구간 A분 동안 B초당 C원
		long first = Math.min(totalSeconds, firstInterval);

		// 2. 두 번째 구간 A분 ~ D분 동안 B초당 E원
		long second = 0L;
		if (totalSeconds > first) {
			second = Math.min(totalSeconds, secondInterval) - first;
		}

		// 3. 세 번째 구간 D분 이후 B초당 F원
		long third = 0L;
		if (totalSeconds > second + first) {
			third = totalSeconds - (second + first);
		}
		Money firstFee = firstAmount.times(first);
		Money secondFee = secondAmount.times(second);
		Money thirdFee = thirdAmount.times(third);

		return firstFee.plus(secondFee).plus(thirdFee);
	}
}
