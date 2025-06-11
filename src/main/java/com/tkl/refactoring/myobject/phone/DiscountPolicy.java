package com.tkl.refactoring.myobject.phone;

import com.tkl.refactoring.myobject.common.Call;
import com.tkl.refactoring.myobject.common.Money;

public interface DiscountPolicy {
    Money calculateFee(Call call);
}
