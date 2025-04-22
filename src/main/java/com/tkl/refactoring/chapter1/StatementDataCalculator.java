package com.tkl.refactoring.chapter1;

import java.util.List;
import java.util.Map;

public class StatementDataCalculator {
    public StatementData createStatementData(Invoice invoice, Map<String, Play> plays) {
        return new StatementData(invoice.customer(),
                                                        enrichPerformances(invoice.performances(), plays),
                                                        getTotalAmounts(enrichPerformances(invoice.performances(), plays)),
                                                        getTotalVolumeCredits(enrichPerformances(invoice.performances(), plays)));
    }

    private List<EnrichedPerformance> enrichPerformances(List<Performance> performances, Map<String, Play> plays) {
        return performances.stream()
                            .map(p -> new EnrichedPerformance(new Performance(p.playID(), p.audience()),
                                                                findByPerformancePlayId(plays, p),
                                                                getAmounts(p, findByPerformancePlayId(plays, p)),
                                                                getVolumeCredits(p, findByPerformancePlayId(plays, p))))
                            .toList();
    }


    private int getTotalAmounts(List<EnrichedPerformance> enrichedPerformances) {
        return enrichedPerformances.stream()
                                    .mapToInt(EnrichedPerformance::amount)
                                    .sum();
    }

    private int getTotalVolumeCredits(List<EnrichedPerformance> enrichedPerformances) {
        return enrichedPerformances.stream()
                                    .mapToInt(EnrichedPerformance::volumeCredits)
                                    .sum();
    }

    private int getVolumeCredits(Performance perf, Play play) {
        return new PerformanceCalculator(perf, play).getVolumeCredits();
    }

    private Play findByPerformancePlayId(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.playID());
    }

    private int getAmounts(Performance perf, Play play) {
        return new PerformanceCalculator(perf, play).getAmounts();
    }
}
