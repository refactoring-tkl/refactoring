package com.tkl.refactoring.object.phone;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPhone {
    private final List<Call> calls = new ArrayList<>();
    private final List<ExtraPolicy> extraPolicies = new ArrayList<>();

    public Money calculateFee() {
        Money result = Money.ZERO;

        for (Call call : calls) {
            result = result.plus(calculateFee(call));
        }

        for (ExtraPolicy extraPolicy : extraPolicies) {
            result = applyPolicy(extraPolicy, result);
        }
        return result;
    }

    public void addExtraPolicy(ExtraPolicy extraPolicy) {
        extraPolicies.add(extraPolicy);
    }

    public void addExtraPolicies(List<ExtraPolicy> extraPolicies) {
        this.extraPolicies.addAll(extraPolicies);
    }

    abstract Money applyPolicy(ExtraPolicy extraPolicy, Money currentFee);

    abstract protected Money calculateFee(Call call);

    public void call(Call call) { calls.add(call); }
    public List<Call> getCalls() { return calls; }
}
