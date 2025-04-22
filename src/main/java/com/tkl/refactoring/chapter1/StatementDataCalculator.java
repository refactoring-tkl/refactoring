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
                                                                getVolumeCredits(findByPerformancePlayId(plays, p), p)))
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

    private int getVolumeCredits(Play play, Performance performance) {
        // add volume credits
        int volumeCredits = Math.max(performance.audience() - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type())) volumeCredits += performance.audience() / 5;
        return volumeCredits;
    }

    private Play findByPerformancePlayId(Map<String, Play> plays, Performance perf) {
        return plays.get(perf.playID());
    }

    private int getAmounts(Performance perf, Play play) {
        int amounts = 0;
        switch (play.type()) {
            case "tragedy":
                amounts = 40000;
                if (perf.audience() > 30) {
                    amounts += 1000 * (perf.audience() - 30);
                }
                break;
            case "comedy":
                amounts = 30000;
                if (perf.audience() > 20) {
                    amounts += 10000 + 500 * (perf.audience() - 20);
                }
                amounts += 300 * perf.audience();
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return amounts;
    }
}
