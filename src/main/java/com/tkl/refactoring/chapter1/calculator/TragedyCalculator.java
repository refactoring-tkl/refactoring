package com.tkl.refactoring.chapter1.calculator;

import com.tkl.refactoring.chapter1.Performance;
import com.tkl.refactoring.chapter1.Play;
import com.tkl.refactoring.chapter1.StatementPrinter;

public class TragedyCalculator extends PerformanceCalculator {
    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }
    @Override
    public int amount() {
        int result = 40000;
        if (performance.audience() > 30) {
            result += 1000 * (performance.audience() - 30);
        }
        return result;
    }

    @Override
    public int volumeCredits() {
        int result = 0;
        result += Math.max(performance.audience() - 30, 0);
        return result;
    }
}
