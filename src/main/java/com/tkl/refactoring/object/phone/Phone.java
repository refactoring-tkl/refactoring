package com.tkl.refactoring.object.phone;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.fee.FeePerDuration;
import com.tkl.refactoring.object.fee.FeeRule;
import com.tkl.refactoring.object.fee.condition.DayOfWeekFeeCondition;
import com.tkl.refactoring.object.fee.condition.DurationFeeCondition;
import com.tkl.refactoring.object.fee.condition.FixedFeeCondition;
import com.tkl.refactoring.object.fee.condition.TimeOfDayFeeCondition;
import com.tkl.refactoring.object.policy.addition.RateDiscountablePolicy;
import com.tkl.refactoring.object.policy.addition.TaxablePolicy;
import com.tkl.refactoring.object.policy.basic.BasicRatePolicy;
import com.tkl.refactoring.object.policy.RatePolicy;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Phone {
    private RatePolicy ratePolicy;
    private List<Call> calls = new ArrayList<>();

    public Phone(RatePolicy ratePolicy) {
        this.ratePolicy = ratePolicy;
    }

    public void call(Call call) { calls.add(call); }
    public List<Call> getCalls() { return Collections.unmodifiableList(calls); }

    public Money calculateFee() {
        return ratePolicy.calculateFee(this);
    }

    public static void main(String[] args) {
        Phone taxablFixedFeePhone
            = new Phone(
                new TaxablePolicy(0.05,
                    new BasicRatePolicy(
                        new FeeRule(
                            new FixedFeeCondition(),
                                new FeePerDuration(Money.wons(10), Duration.ofSeconds(10))))));

        Phone taxableRateDiscountableTimeOfDayFeePhone
            = new Phone(
                new TaxablePolicy(0.05,
                    new RateDiscountablePolicy(Money.wons(1000),
                        new BasicRatePolicy(
                            new FeeRule(
                                new TimeOfDayFeeCondition(LocalTime.of(0,0,0), LocalTime.of(18,59,59)),
                                    new FeePerDuration(Money.wons(18), Duration.ofSeconds(10))),
                            new FeeRule(
                                new TimeOfDayFeeCondition(LocalTime.of(19,0,0), LocalTime.of(23, 59,59)),
                                    new FeePerDuration(Money.wons(15), Duration.ofSeconds(10)))))));

        Phone rateDiscountableTaxableDayOfWeekFeePhone
            = new Phone(
                new RateDiscountablePolicy(Money.wons(1000),
                    new TaxablePolicy(0.05,
                        new BasicRatePolicy(
                            new FeeRule(
                                new DayOfWeekFeeCondition(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),
                                    new FeePerDuration(Money.wons(38), Duration.ofSeconds(10))),
                            new FeeRule(
                                new DayOfWeekFeeCondition(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
                                    new FeePerDuration(Money.wons(19), Duration.ofSeconds(10)))))));

        Phone rateDiscountableTaxableNightlyDiscountPhone
            = new Phone(
                new RateDiscountablePolicy(Money.wons(1000),
                    new TaxablePolicy(0.05,
                        new BasicRatePolicy(
                            new FeeRule(
                                new DurationFeeCondition(Duration.ofSeconds(0), Duration.ofMinutes(1)),
                                    new FeePerDuration(Money.wons(50), Duration.ofSeconds(10))),
                            new FeeRule(
                                new DurationFeeCondition(Duration.ofMinutes(1), Duration.ofDays(365)),
                                    new FeePerDuration(Money.wons(20), Duration.ofSeconds(10)))))));
    }
}
