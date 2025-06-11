package com.tkl.refactoring.chapter1.calculator;

import com.tkl.refactoring.chapter1.Performance;
import com.tkl.refactoring.chapter1.Play;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class PerformanceCalculator {
    protected Performance performance;
    protected Play play;

    public abstract int amount();
    public abstract int volumeCredits();
}