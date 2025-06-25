package com.tkl.refactoring.myobject.phone;

import com.tkl.refactoring.myobject.common.Money;

public abstract class AdditionalDiscountPolicy implements DiscountPolicy {
    protected DiscountPolicy discountPolicy;

    public AdditionalDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
