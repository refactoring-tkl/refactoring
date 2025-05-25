package com.tkl.refactoring.object.phone;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.policy.addition.RateDiscountablePolicy;
import com.tkl.refactoring.object.policy.addition.TaxablePolicy;
import com.tkl.refactoring.object.policy.basic.NightlyDiscountPolicy;
import com.tkl.refactoring.object.policy.RatePolicy;
import com.tkl.refactoring.object.policy.basic.RegularPolicy;

import java.time.Duration;
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
        Phone taxableRegularPhone = new Phone(
                                        new TaxablePolicy(0.05,
                                            new RegularPolicy(Money.wons(10), Duration.ofSeconds(10))));
        Phone taxableRateDiscountableRegularPhone = new Phone(
                                                        new TaxablePolicy(0.05,
                                                            new RateDiscountablePolicy(Money.wons(1000),
                                                                new RegularPolicy(Money.wons(10), Duration.ofSeconds(10)))));

        Phone rateDiscountableTaxableRegularPhone = new Phone(
                                                        new RateDiscountablePolicy(Money.wons(1000),
                                                            new TaxablePolicy(0.05,
                                                                new RegularPolicy(Money.wons(10), Duration.ofSeconds(10)))));
        Phone rateDiscountableTaxableNightlyDiscountPhone = new Phone(
                                                                new RateDiscountablePolicy(Money.wons(1000),
                                                                    new TaxablePolicy(0.05,
                                                                        new NightlyDiscountPolicy(Money.wons(5), Money.wons(10), Duration.ofSeconds(10)))));
    }
}
