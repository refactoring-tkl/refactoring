package com.tkl.refactoring.chapter1.calculator;

import com.tkl.refactoring.chapter1.Performance;
import com.tkl.refactoring.chapter1.Play;
import com.tkl.refactoring.chapter1.StatementPrinter;

public class PerformanceCalculatorFactory {
    public PerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
        switch (play.type()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            default:
                throw new Error("unknown type: ${playFor(performance).type()}");
        }
    }
}
