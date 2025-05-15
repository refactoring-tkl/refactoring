package com.tkl.refactoring.chapter1.calculator;

import com.tkl.refactoring.chapter1.model.Performance;
import com.tkl.refactoring.chapter1.model.Play;

public class PerformanceCalculatorFactory {
    private PerformanceCalculatorFactory() {
        throw new UnsupportedOperationException("This cannot be instantiated");
    }

    public static PerformanceCalculator createPerformanceCalculator(Performance perf, Play play) {
        return switch (play.type()) {
            case "tragedy" -> new TragedyCalculator(perf, play);
            case "comedy" -> new ComedyCalculator(perf, play);
            default -> throw new IllegalArgumentException("Unknown type of play");
        };
    }
}
