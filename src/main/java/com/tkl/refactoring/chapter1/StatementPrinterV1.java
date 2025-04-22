package com.tkl.refactoring.chapter1;

import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinterV1 {

    @Getter
    static class StatementData {
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
        private int amountFor(Performance performance) {
            int result;
            switch (playFor(performance).type()) {
                case "tragedy":
                    result = 40000;
                    if (performance.audience() > 30) {
                        result += 1000 * (performance.audience() - 30);
                    }
                    break;
                case "comedy":
                    result = 30000;
                    if (performance.audience() > 20) {
                        result += 10000 + 500 * (performance.audience() - 20);
                    }
                    result += 300 * performance.audience();
                    break;
                default:
                    throw new Error("unknown type: ${playFor(performance).type()}");
            }
            return result;
        }
        private int totalAmount(StatementData statementData) {
            return statementData.performances.stream()
                    .mapToInt(this::amountFor)
                    .sum();
        }
        private int volumeCreditsFor(Performance performance) {
            int result = 0;
            // add volume credits
            result += Math.max(performance.audience() - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(playFor(performance).type())) result += performance.audience() / 5;
            return result;
        }
        private int totalVolumeCredits(StatementData statementData) {
            return statementData.getPerformances().stream()
                    .mapToInt(this::volumeCreditsFor)
                    .sum();
        }
    }

    public String print(Invoice invoice, Map<String, Play> plays) {
        StatementData statementData = new StatementPrinterV1.StatementData(invoice, plays);
        return renderPlainText(statementData, plays);
    }

    private String renderPlainText(StatementData statementData, Map<String, Play> plays) {
        String result = String.format("Statement for %s\n", statementData.getCustomer());

        for (Performance performance : statementData.getPerformances()) {
            result += String.format("  %s: %s (%s seats)\n", statementData.playFor(performance).name(), usd(statementData.amountFor(performance)), performance.audience());
        }

        result += String.format("Amount owed is %s\n", usd(statementData.totalAmount(statementData)));
        result += String.format("You earned %s credits\n", statementData.totalVolumeCredits(statementData));
        return result;
    }

    private String usd(int amount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount / 100);
    }

}
