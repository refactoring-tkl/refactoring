package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {
    public String print(Invoice invoice, Map<String, Play> plays) {
        StatementData statementData = new StatementData(invoice.customer(),
                                                        enrichPerformances(invoice.performances(), plays),
                                                        getTotalAmounts(enrichPerformances(invoice.performances(), plays)),
                                                        getTotalVolumeCredits(enrichPerformances(invoice.performances(), plays)));
        return renderPlainText(statementData);
    }

    private List<EnrichedPerformance> enrichPerformances(List<Performance> performances, Map<String, Play> plays) {
        return performances.stream()
                            .map(p -> new EnrichedPerformance(new Performance(p.playID(), p.audience()),
                                                                findByPerformancePlayId(plays, p),
                                                                getAmounts(p, findByPerformancePlayId(plays, p)),
                                                                getVolumeCredits(findByPerformancePlayId(plays, p), p)))
                            .toList();
    }

    private String renderPlainText(StatementData data) {
        String result = String.format("Statement for %s\n", data.customer());
        for (EnrichedPerformance enrichedPerf : data.enrichedPerformances()) {
            // print line for this order
            result += String.format("  %s: %s (%s seats)\n",
                                        enrichedPerf.play().name(),
                                        usd().format(enrichedPerf.amount() / 100),
                                        enrichedPerf.performance().audience());
        }
        result += String.format("Amount owed is %s\n", usd().format(data.totalAmounts() / 100));
        result += String.format("You earned %s credits\n", data.totalVolumeCredits());
        return result;
    }

    private int getTotalAmounts(List<EnrichedPerformance> enrichedPerformances) {
        int amounts = 0;
        for (EnrichedPerformance enrichedPerf : enrichedPerformances) {
            amounts += enrichedPerf.amount();
        }
        return amounts;
    }

    private int getTotalVolumeCredits(List<EnrichedPerformance> enrichedPerformances) {
        return enrichedPerformances.stream()
                                    .mapToInt(EnrichedPerformance::volumeCredits)
                                    .sum();
    }

    private NumberFormat usd() {
        return NumberFormat.getCurrencyInstance(Locale.US);
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
