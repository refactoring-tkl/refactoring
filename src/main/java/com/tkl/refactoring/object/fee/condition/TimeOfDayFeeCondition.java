package com.tkl.refactoring.object.fee.condition;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.DateTimeInterval;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class TimeOfDayFeeCondition implements FeeCondition {
    private final LocalTime from;
    private final LocalTime to;

    public TimeOfDayFeeCondition(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public List<DateTimeInterval> findTimeIntervals(Call call) {
        return call.getInterval()
                .splitByDay()
                .stream()
                .map(each ->
                    DateTimeInterval.of(
                        LocalDateTime.of(each.getFrom().toLocalDate(), from(each)),
                        LocalDateTime.of(each.getTo().toLocalDate(), to(each))))
                .collect(Collectors.toUnmodifiableList());
    }

    private LocalTime from(DateTimeInterval interval) {
        return interval.getFrom().toLocalTime().isBefore(from) ?
            from : interval.getFrom().toLocalTime();
    }

    private LocalTime to(DateTimeInterval interval) {
        return interval.getTo().toLocalTime().isAfter(to) ?
            to : interval.getTo().toLocalTime();
    }
}
