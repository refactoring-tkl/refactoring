package com.tkl.refactoring.myobject.phone;

import com.tkl.refactoring.myobject.common.Call;
import com.tkl.refactoring.myobject.common.Money;

public class BasicDiscountPolicy extends AdditionalDiscountPolicy{
    private Money discount;

    public BasicDiscountPolicy(DiscountPolicy discountPolicy, Money discount) {
        super(discountPolicy);
        this.discount = discount;
    }

    @Override
    public Money calculateFee(Call call) {
        return null;
    }
}
