package com.tkl.refactoring.myobject.phone;

import com.tkl.refactoring.myobject.common.Call;
import com.tkl.refactoring.myobject.common.Money;

public class TaxPolicy extends AdditionalDiscountPolicy{
    Money discount;

    public TaxPolicy(DiscountPolicy discountPolicy, Money discount) {
        super(discountPolicy);
        this.discount = discount;
    }

    @Override
    public Money calculateFee(Call call) {
        return null;
    }
}
