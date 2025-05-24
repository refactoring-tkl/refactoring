package com.tkl.refactoring.object.phone;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPhone {
    private final List<Call> calls = new ArrayList<>();

    public Money calculateFee() {
        Money result = Money.ZERO;

        for (Call call : calls) {
            result = result.plus(calculateFee(call));
        }
        return result;
    }

    abstract protected Money calculateFee(Call call);

    public void call(Call call) { calls.add(call); }
    public List<Call> getCalls() { return calls; }
}
