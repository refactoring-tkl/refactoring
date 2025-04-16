package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        StatementData statementData = new StatementData(invoice, plays);
        return renderPlainText(statementData, plays);
    }

    private String renderPlainText(StatementData statementData, Map<String, Play> plays) {
        String result = String.format("Statement for %s\n", statementData.customer());

        for (Performance perf : statementData.performances()) {
            Play play = plays.get(perf.playID());

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name(), usd(amountFor(perf, play)), perf.audience());
        }

        result += String.format("Amount owed is %s\n", usd(totalAmount(statementData.performances(), plays)));
        result += String.format("You earned %s credits\n", totalVolumeCredits(statementData.performances(), plays));
        return result;
    }

    private int totalAmount(List<Performance> performances, Map<String, Play> plays) {
        int result = 0;
        for (Performance perf : performances) {
            Play play = plays.get(perf.playID());
            int thisAmount = amountFor(perf, play);

            result += thisAmount;
        }
        return result;
    }

    private int totalVolumeCredits(List<Performance> performances, Map<String, Play> plays) {
        int result = 0;
        for (Performance perf : performances) {
            Play play = plays.get(perf.playID());
            result += volumeCreditsFor(perf, play);
        }
        return result;
    }

    private static String usd(int thisAmount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(thisAmount / 100);
    }

    private static int volumeCreditsFor(Performance perf, Play play) {
        int result = 0;
        // add volume credits
        result += Math.max(perf.audience() - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(play.type())) result += perf.audience() / 5;
        return result;
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

    private static class StatementData {
        private final List<Performance> performances;
        private final Map<String, Play> plays;
        private final String customer;

        public StatementData(Invoice invoice, Map<String, Play> plays) {
            this.customer = invoice.customer();
            this.performances = Collections.unmodifiableList(invoice.performances());
            this.plays = plays;
        }

        public List<Performance> performances() {
            return performances;
        }

        public Map<String, Play> plays() {
            return plays;
        }

        public String customer() {
            return customer;
        }

    }
}
