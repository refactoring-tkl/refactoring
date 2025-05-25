package com.tkl.refactoring.object.policy.addition;

import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.policy.RatePolicy;

public class TaxablePolicy extends AdditionalRatePolicy {
    private final double taxRatio;

    public TaxablePolicy(double taxRatio, RatePolicy next) {
        super(next);
        this.taxRatio = taxRatio;
    }

    @Override
    protected Money afterCalculated(Money fee) {
        return fee.plus(fee.times(taxRatio));
    }
}