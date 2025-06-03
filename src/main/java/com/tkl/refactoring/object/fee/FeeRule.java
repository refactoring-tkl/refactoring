package com.tkl.refactoring.object.fee;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.fee.condition.FeeCondition;

public class FeeRule {
    private final FeeCondition feeCondition;
    private final FeePerDuration feePerDuration;

    public FeeRule(FeeCondition feeCondition, FeePerDuration feePerDuration) {
        this.feeCondition = feeCondition;
        this.feePerDuration = feePerDuration;
    }

    public Money calculateFee(Call call) {
        return feeCondition.findTimeIntervals(call)
                .stream()
                .map(each -> feePerDuration.calculate(each))
                .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }
}
