package com.tkl.refactoring.object.fee;

import com.tkl.refactoring.object.common.DateTimeInterval;
import com.tkl.refactoring.object.common.Money;

import java.time.Duration;

public class FeePerDuration {
    private final Money fee;
    private final Duration duration;

    public FeePerDuration(Money fee, Duration duration) {
        this.fee = fee;
        this.duration = duration;
    }

    public Money calculate(DateTimeInterval interval) {
        return fee.times(interval.duration().getSeconds() / duration.getSeconds());
    }
}
