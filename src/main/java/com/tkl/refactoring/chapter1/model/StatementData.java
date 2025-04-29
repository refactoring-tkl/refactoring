package com.tkl.refactoring.chapter1.model;

import com.tkl.refactoring.chapter1.Invoice;
import com.tkl.refactoring.chapter1.Performance;
import com.tkl.refactoring.chapter1.Play;
import com.tkl.refactoring.chapter1.StatementPrinter;
import com.tkl.refactoring.chapter1.calculator.PerformanceCalculatorFactory;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class StatementData {
    private String customer;
    private List<Performance> performances;
    private Map<String, Play> plays;

    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.customer = invoice.customer();
        this.performances = invoice.performances();
        this.plays = plays;
    }

    public Play playFor(Performance performance) {
        return plays.get(performance.playID());
    }
    public int amountFor(Performance performance) {
        return new PerformanceCalculatorFactory().createPerformanceCalculator(performance, playFor(performance)).amount();
    }
    public int volumeCreditsFor(Performance performance) {
        return new PerformanceCalculatorFactory().createPerformanceCalculator(performance, playFor(performance)).volumeCredits();
//            return new PerformanceCalculator(performance, playFor(performance)).volumeCredits();
    }
    public int totalAmount(StatementData statementData) {
        return statementData.performances.stream()
                .mapToInt(this::amountFor)
                .sum();
    }
    public int totalVolumeCredits(StatementData statementData) {
        return statementData.getPerformances().stream()
                .mapToInt(this::volumeCreditsFor)
                .sum();
    }
}
