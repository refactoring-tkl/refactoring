package com.tkl.refactoring.chapter1.calculator;

import com.tkl.refactoring.chapter1.model.Performance;
import com.tkl.refactoring.chapter1.model.Play;

public class TragedyCalculator extends PerformanceCalculator {
    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int getAmounts() {
        int amounts = 40000;
        if (this.performance.audience() > 30) {
            amounts += 1000 * (this.performance.audience() - 30);
        }
        return amounts;
    }
}
