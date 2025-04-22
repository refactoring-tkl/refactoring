package com.tkl.refactoring.chapter1;

public class PerformanceCalculator {
    private final Performance performance;
    private final Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public int getAmounts() {
        int amounts = 0;
        switch (this.play.type()) {
            case "tragedy":
                amounts = 40000;
                if (this.performance.audience() > 30) {
                    amounts += 1000 * (this.performance.audience() - 30);
                }
                break;
            case "comedy":
                amounts = 30000;
                if (this.performance.audience() > 20) {
                    amounts += 10000 + 500 * (this.performance.audience() - 20);
                }
                amounts += 300 * this.performance.audience();
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return amounts;
    }

    public int getVolumeCredits() {
        // add volume credits
        int volumeCredits = Math.max(this.performance.audience() - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(this.play.type())) volumeCredits += this.performance.audience() / 5;
        return volumeCredits;
    }
}
