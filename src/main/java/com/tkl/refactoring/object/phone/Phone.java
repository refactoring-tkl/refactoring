package com.tkl.refactoring.object.phone;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.policy.addition.RateDiscountablePolicy;
import com.tkl.refactoring.object.policy.addition.TaxablePolicy;
import com.tkl.refactoring.object.policy.RatePolicy;
import com.tkl.refactoring.object.policy.basic.FixedFeePolicy;
import com.tkl.refactoring.object.policy.basic.TimeOfDayDiscountPolicy;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Phone {
    private final RatePolicy ratePolicy;
    private final List<Call> calls = new ArrayList<>();

    public Phone(RatePolicy ratePolicy) {
        this.ratePolicy = ratePolicy;
    }

    public void call(Call call) { calls.add(call); }
    public List<Call> getCalls() { return Collections.unmodifiableList(calls); }

    public Money calculateFee() {
        return ratePolicy.calculateFee(this);
    }

    public static void main(String[] args) {
        Phone taxableFixedFeePhone = new Phone(
                                        new TaxablePolicy(0.05,
                                            new FixedFeePolicy(Money.wons(10), Duration.ofSeconds(10))));
        Phone taxableRateDiscountableFixedFeePhone = new Phone(
                                                        new TaxablePolicy(0.05,
                                                            new RateDiscountablePolicy(Money.wons(1000),
                                                                new FixedFeePolicy(Money.wons(10), Duration.ofSeconds(10)))));

        Phone rateDiscountableTaxableFixedFeePhone = new Phone(
                                                        new RateDiscountablePolicy(Money.wons(1000),
                                                            new TaxablePolicy(0.05,
                                                                new FixedFeePolicy(Money.wons(10), Duration.ofSeconds(10)))));
        Phone rateDiscountableTaxableTimeOfDayDiscountPhone = new Phone(
                                                                new RateDiscountablePolicy(Money.wons(1000),
                                                                    new TaxablePolicy(0.05,
                                                                        new TimeOfDayDiscountPolicy(List.of(LocalTime.of(0,0, 0),
                                                                                                            LocalTime.of(19,0, 0)),
                                                                                                    List.of(LocalTime.of(19,0, 1),
                                                                                                            LocalTime.of(23, 59, 59)),
                                                                                                    List.of(Duration.ofSeconds(10),
                                                                                                            Duration.ofSeconds(10)),
                                                                                                    List.of(Money.wons(18),
                                                                                                            Money.wons(15))))));
    }
}
