package com.tkl.refactoring.object.phone;

import com.tkl.refactoring.object.common.Money;

public class TaxPolicy implements ExtraPolicy {
    @Override
    public Money apply(NightlyDiscountPhone nightlyDiscountPhone, Money currentFee) {
        return currentFee.times(1.2);
    }

    @Override
    public Money apply(Phone phone, Money currentFee) {
        return currentFee.times(1.1);
    }
}
