package com.tkl.refactoring.object.phone;

import java.time.Duration;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;

public class Phone extends AbstractPhone {
    private final Money amount;
    private final Duration seconds;

    public Phone(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    Money applyPolicy(ExtraPolicy extraPolicy, Money currentFee) {
        return extraPolicy.apply(this, currentFee);
    }

    @Override
    protected Money calculateFee(Call call) {
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
}
