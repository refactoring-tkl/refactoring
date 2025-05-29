package com.tkl.refactoring.object.phone;

import com.tkl.refactoring.object.common.Money;

public interface ExtraPolicy {

    Money apply(NightlyDiscountPhone nightlyDiscountPhone, Money currentFee);
    Money apply(Phone phone, Money currentFee);
}
