package com.tkl.refactoring.object.policy.addition;

import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.phone.Phone;
import com.tkl.refactoring.object.policy.RatePolicy;

public abstract class AdditionalRatePolicy implements RatePolicy {
    private final RatePolicy next;

    public AdditionalRatePolicy(RatePolicy next) {
        this.next = next;
    }

    @Override
    public Money calculateFee(Phone phone) {
        Money fee = next.calculateFee(phone);
        return afterCalculated(fee);
    }

    abstract protected Money afterCalculated(Money fee);
}
