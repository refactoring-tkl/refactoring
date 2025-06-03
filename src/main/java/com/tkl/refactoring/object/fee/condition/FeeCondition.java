package com.tkl.refactoring.object.fee.condition;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.DateTimeInterval;

import java.util.List;

public interface FeeCondition {
    List<DateTimeInterval> findTimeIntervals(Call call);
}
