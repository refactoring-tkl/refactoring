package com.tkl.refactoring.chapter1.calculator;

import com.tkl.refactoring.chapter1.Performance;
import com.tkl.refactoring.chapter1.Play;

public class ComedyCalculator extends PerformanceCalculator{
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }
    @Override
    public int amount() {
        int result = 30000;
        if (performance.audience() > 20) {
            result += 10000 + 500 * (performance.audience() - 20);
        }
        result += 300 * performance.audience();
        return result;
    }

    @Override
    public int volumeCredits() {
        int result = 0;
        result += Math.max(performance.audience() - 30, 0);
        result += performance.audience() / 5;
        return result;
    }
}