package com.tkl.refactoring.object.policy.basic;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.fee.FeeRule;
import com.tkl.refactoring.object.phone.Phone;
import com.tkl.refactoring.object.policy.RatePolicy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicRatePolicy implements RatePolicy {
    private List<FeeRule> feeRules = new ArrayList<>();

    public BasicRatePolicy(FeeRule ... feeRules) {
        this.feeRules = Arrays.asList(feeRules);
    }

    @Override
    public Money calculateFee(Phone phone) {
        return phone.getCalls()
                .stream()
                .map(call -> calculate(call))
                .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }

    private Money calculate(Call call) {
        return feeRules
                .stream()
                .map(rule -> rule.calculateFee(call))
                .reduce(Money.ZERO, (first, second) -> first.plus(second));
    }
}
