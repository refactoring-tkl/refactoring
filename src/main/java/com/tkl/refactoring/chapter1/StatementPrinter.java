package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {
    public String print(Invoice invoice, Map<String, Play> plays) {
        StatementData statementData = new StatementData(invoice.customer(),
                                                        enrichPerformances(invoice.performances()));
        return renderPlainText(statementData, plays);
    }

    private List<EnrichedPerformance> enrichPerformances(List<Performance> performances) {
        return performances.stream()
                            .map(p -> new EnrichedPerformance(new Performance(p.playID(), p.audience())))
                            .toList();
    }

    private String renderPlainText(StatementData data, Map<String, Play> plays) {
        String result = String.format("Statement for %s\n", data.customer());
        for (EnrichedPerformance enrichedPerf : data.enrichedPerformances()) {
            // print line for this order
            result += String.format("  %s: %s (%s seats)\n",
                findByPerformancePlayId(plays, enrichedPerf.performance()).name(),
                usd().format(getAmounts(enrichedPerf.performance(),
                            findByPerformancePlayId(plays, enrichedPerf.performance())) / 100),
                enrichedPerf.performance().audience());
        }
        result += String.format("Amount owed is %s\n", usd().format(getAmounts(data.enrichedPerformances(), plays) / 100));
        result += String.format("You earned %s credits\n", getVolumeCredits(data.enrichedPerformances(), plays));
        return result;
    }

    private int getAmounts(List<EnrichedPerformance> enrichedPerformances, Map<String, Play> plays) {
        int amounts = 0;
        for (EnrichedPerformance enrichedPerf : enrichedPerformances) {
            amounts += getAmounts(enrichedPerf.performance(), findByPerformancePlayId(plays, enrichedPerf.performance()));
        }
        return amounts;
    }

    private int getVolumeCredits(List<EnrichedPerformance> enrichedPerformances, Map<String, Play> plays) {
        int volumeCredits = 0;
        for (EnrichedPerformance enrichedPerf : enrichedPerformances) {
            volumeCredits += getVolumeCredits(plays, enrichedPerf.performance());
        }
        return volumeCredits;
    }

    private NumberFormat usd() {
        return NumberFormat.getCurrencyInstance(Locale.US);
    }

    private int getVolumeCredits(Map<String, Play> plays, Performance perf) {
        // add volume credits
        int volumeCredits = Math.max(perf.audience() - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(findByPerformancePlayId(plays, perf).type())) volumeCredits += perf.audience() / 5;
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
