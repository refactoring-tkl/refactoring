package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        int totalAmount = 0;
        String result = String.format("Statement for %s\n", invoice.customer());

        for (Performance perf : invoice.performances()) {
            // print line for this order
            result += String.format("  %s: %s (%s seats)\n",
                findByPerformancePlayId(plays, perf).name(),
                usd().format(getAmounts(perf, findByPerformancePlayId(plays, perf)) / 100),
                perf.audience());
            totalAmount += getAmounts(perf, findByPerformancePlayId(plays, perf));
        }
        int volumeCredits = 0;
        for (Performance perf : invoice.performances()) {
            volumeCredits += getVolumeCredits(plays, perf);
        }
        result += String.format("Amount owed is %s\n", usd().format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
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
