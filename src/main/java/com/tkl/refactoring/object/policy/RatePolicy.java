package com.tkl.refactoring.object.policy;

import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.phone.Phone;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}
