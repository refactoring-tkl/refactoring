package com.tkl.refactoring.object.policy.addition;

import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.policy.RatePolicy;

public class RateDiscountablePolicy extends AdditionalRatePolicy {
    private final Money discountAmount;

    public RateDiscountablePolicy(Money discountAmount, RatePolicy next) {
        super(next);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return fee.minus(discountAmount);
    }
}
