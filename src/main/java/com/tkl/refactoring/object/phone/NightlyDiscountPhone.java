package com.tkl.refactoring.object.phone;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;

import java.time.Duration;

public class NightlyDiscountPhone extends AbstractPhone {
    private static final int LATE_NIGHT_HOUR = 22;

    private final Money nightlyAmount;
    private final Money regularAmount;
    private final Duration seconds;

    public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds) {
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.seconds = seconds;
    }

    @Override
    Money applyPolicy(ExtraPolicy extraPolicy, Money currentFee) {
        return extraPolicy.apply(this, currentFee);
    }

    @Override
    protected Money calculateFee(Call call) {
        if (isLateNightHour(call)) {
            return nightlyAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
        }
        return regularAmount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }

    boolean isLateNightHour(Call call) {
        return call.getFrom().getHour() >= LATE_NIGHT_HOUR;
    }
}
