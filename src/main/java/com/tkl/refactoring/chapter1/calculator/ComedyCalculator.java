package com.tkl.refactoring.chapter1.calculator;

import com.tkl.refactoring.chapter1.model.Performance;
import com.tkl.refactoring.chapter1.model.Play;

public class ComedyCalculator extends PerformanceCalculator {
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmounts() {
        int amounts = 30000;
        if (this.performance.audience() > 20) {
            amounts += 10000 + 500 * (this.performance.audience() - 20);
        }
        amounts += 300 * this.performance.audience();
        return amounts;
    }
}
