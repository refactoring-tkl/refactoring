package com.tkl.refactoring.chapter1;

import com.tkl.refactoring.chapter1.calculator.ComedyCalculator;
import com.tkl.refactoring.chapter1.calculator.PerformanceCalculator;
import com.tkl.refactoring.chapter1.calculator.PerformanceCalculatorFactory;
import com.tkl.refactoring.chapter1.calculator.TragedyCalculator;
import com.tkl.refactoring.chapter1.model.*;

import java.util.List;
import java.util.Map;

public class StatementDataBuilder {
    private StatementDataBuilder() {
        throw new UnsupportedOperationException("This cannot be instantiated");
    }

    public static StatementData createStatementData(Invoice invoice, Map<String, Play> plays) {
        return new StatementData(invoice.customer(),
                enrichPerformances(invoice.performances(), plays),
                getTotalAmounts(enrichPerformances(invoice.performances(), plays)),
                getTotalVolumeCredits(enrichPerformances(invoice.performances(), plays)));
    }

    private static List<EnrichedPerformance> enrichPerformances(List<Performance> performances, Map<String, Play> plays) {
        return performances.stream()
                .map(p -> {
                    PerformanceCalculator calculator = PerformanceCalculatorFactory.createPerformanceCalculator(p, findByPerformancePlayId(plays, p));
                    return new EnrichedPerformance(new Performance(p.playID(), p.audience()),
                            findByPerformancePlayId(plays, p),
                            calculator.getAmounts(),
                            calculator.getVolumeCredits());
                })
                .toList();
    }

    private static int getTotalAmounts(List<EnrichedPerformance> enrichedPerformances) {
        return enrichedPerformances.stream()
                .mapToInt(EnrichedPerformance::amount)
                .sum();
    }

    private static int getTotalVolumeCredits(List<EnrichedPerformance> enrichedPerformances) {
        return enrichedPerformances.stream()
                .mapToInt(EnrichedPerformance::volumeCredits)
                .sum();
    }

    private static Play findByPerformancePlayId(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.playID());
    }
}

