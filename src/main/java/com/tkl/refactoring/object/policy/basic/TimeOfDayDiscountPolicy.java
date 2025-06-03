package com.tkl.refactoring.object.policy.basic;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.DateTimeInterval;
import com.tkl.refactoring.object.common.Money;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeOfDayDiscountPolicy extends BasicRatePolicy {
    private List<LocalTime> starts = new ArrayList<>();
    private List<LocalTime> ends = new ArrayList<>();
    private List<Duration> durations = new ArrayList<>();
    private List<Money> amounts = new ArrayList<>();

    public TimeOfDayDiscountPolicy(List<LocalTime> starts, List<LocalTime> ends, List<Duration> durations, List<Money> amounts) {
        this.starts = starts;
        this.ends = ends;
        this.durations = durations;
        this.amounts = amounts;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        Money result = Money.ZERO;
        for (DateTimeInterval interval : call.splitByDay()) {
            for (int loop = 0; loop < starts.size(); loop++) {
                result.plus(amounts.get(loop).times(
                    Duration.between(from(interval, starts.get(loop)),
                                to(interval, ends.get(loop)))
                                .getSeconds() / durations.get(loop).getSeconds()));
            }
        }
        return result;
    }

    private LocalTime from(DateTimeInterval interval, LocalTime from) {
        return interval.getFrom().toLocalTime().isBefore(from) ?
            from :
            interval.getFrom().toLocalTime();
    }

    private LocalTime to(DateTimeInterval interval, LocalTime to) {
        return interval.getTo().toLocalTime().isAfter(to) ?
            to :
            interval.getTo().toLocalTime();
    }
}
