package com.tkl.refactoring.object.policy.basic;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;

import java.time.Duration;

public class FixedFeePolicy extends BasicRatePolicy {
    private final Money amount;
    private final Duration seconds;

    public FixedFeePolicy(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
}
