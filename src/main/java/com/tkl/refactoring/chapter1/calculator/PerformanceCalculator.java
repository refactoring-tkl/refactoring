package com.tkl.refactoring.chapter1.calculator;

import com.tkl.refactoring.chapter1.model.Performance;
import com.tkl.refactoring.chapter1.model.Play;

public abstract class PerformanceCalculator {
    protected final Performance performance;
    protected final Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public abstract int getAmounts();

    public int getVolumeCredits() {
        // add volume credits
        int volumeCredits = Math.max(this.performance.audience() - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(this.play.type())) volumeCredits += this.performance.audience() / 5;
        return volumeCredits;
    }
}
