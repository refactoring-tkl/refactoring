package com.tkl.refactoring.chapter1;

import java.util.List;
import java.util.Map;

public class StatementDataBuilder {
    public static StatementData createStatementData(Invoice invoice, Map<String, Play> plays) {
        return new StatementData(invoice.customer(),
                enrichPerformances(invoice.performances(), plays),
                getTotalAmounts(enrichPerformances(invoice.performances(), plays)),
                getTotalVolumeCredits(enrichPerformances(invoice.performances(), plays)));
    }

    private static List<EnrichedPerformance> enrichPerformances(List<Performance> performances, Map<String, Play> plays) {
        return performances.stream()
                .map(p -> {
                    PerformanceCalculator calculator = createPerformanceCalculator(p, findByPerformancePlayId(plays, p));
                    return new EnrichedPerformance(new Performance(p.playID(), p.audience()),
                            findByPerformancePlayId(plays, p),
                            calculator.getAmounts(),
                            calculator.getVolumeCredits());
                })
                .toList();
    }

    private static PerformanceCalculator createPerformanceCalculator(Performance perf, Play play) {
        return switch (play.type()) {
            case "tragedy" -> new TragedyCalculator(perf, play);
            case "comedy" -> new ComedyCalculator(perf, play);
            default -> throw new IllegalArgumentException("Unknown type of play");
        };
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

