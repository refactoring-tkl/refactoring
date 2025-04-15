package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("Statement for %s\n", invoice.customer());

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance perf : invoice.performances()) {
            Play play = plays.get(perf.playID());
            int thisAmount = amountFor(perf, play);
            volumeCredits += volumeCreditsFor(perf, play);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name(), frmt.format(thisAmount / 100), perf.audience());
            totalAmount += thisAmount;
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private static int volumeCreditsFor(Performance perf, Play play) {
        int volumeCredits = 0;
        // add volume credits
        volumeCredits += Math.max(perf.audience() - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type())) volumeCredits += perf.audience() / 5;
        return volumeCredits;
    }

    private int amountFor(Performance perf, Play play) {
        int result;
        switch (play.type()) {
            case "tragedy":
                result = 40000;
                if (perf.audience() > 30) {
                    result += 1000 * (perf.audience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (perf.audience() > 20) {
                    result += 10000 + 500 * (perf.audience() - 20);
                }
                result += 300 * perf.audience();
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }
        return result;
    }

}
