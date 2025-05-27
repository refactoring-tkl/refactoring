package com.tkl.refactoring.myobject.phone;

import com.tkl.refactoring.myobject.common.Call;
import com.tkl.refactoring.myobject.common.Money;

import java.time.Duration;

public class Phone implements DiscountPolicy{
    private final Money amount;
    private final Duration seconds;

    public Phone(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    public Money calculateFee(Call call) {
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
}
