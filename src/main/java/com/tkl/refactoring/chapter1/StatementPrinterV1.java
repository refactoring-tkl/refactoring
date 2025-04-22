package com.tkl.refactoring.chapter1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinterV1 {
    static class PerformanceCalculatorFactory {
        public PerformanceCalculator createPerformanceCalculator(Performance performance, Play play) {
            switch (play.type()) {
                case "tragedy":
                    return new TragedyCalculator(performance, play);
                case "comedy":
                    return new ComedyCalculator(performance, play);
                default:
                    throw new Error("unknown type: ${playFor(performance).type()}");
            }
        }
    }
    @Getter
    @AllArgsConstructor
    static abstract class PerformanceCalculator {
        protected Performance performance;
        protected Play play;

        protected abstract int amount();
        private int volumeCredits() {
            int result = 0;
            result += Math.max(performance.audience() - 30, 0);
            if ("comedy".equals(play.type())) result += performance.audience() / 5;
            return result;
        }
    }

    static class TragedyCalculator extends PerformanceCalculator{
        public TragedyCalculator(Performance performance, Play play) {
            super(performance, play);
        }
        @Override
        public int amount() {
            int result = 40000;
            if (performance.audience() > 30) {
                result += 1000 * (performance.audience() - 30);
            }
            return result;
        }
    }
    static class ComedyCalculator extends PerformanceCalculator{
        public ComedyCalculator(Performance performance, Play play) {
            super(performance, play);
        }
        @Override
        public int amount() {
            int result = 30000;
            if (performance.audience() > 20) {
                result += 10000 + 500 * (performance.audience() - 20);
            }
            result += 300 * performance.audience();
            return result;
        }
    }

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
            return new PerformanceCalculatorFactory().createPerformanceCalculator(performance, playFor(performance)).amount();
        }
        private int volumeCreditsFor(Performance performance) {
            return new PerformanceCalculatorFactory().createPerformanceCalculator(performance, playFor(performance)).volumeCredits();
//            return new PerformanceCalculator(performance, playFor(performance)).volumeCredits();
        }
        private int totalAmount(StatementData statementData) {
            return statementData.performances.stream()
                    .mapToInt(this::amountFor)
                    .sum();
        }
        private int totalVolumeCredits(StatementData statementData) {
            return statementData.getPerformances().stream()
                    .mapToInt(this::volumeCreditsFor)
                    .sum();
        }
    }

    public String print(Invoice invoice, Map<String, Play> plays) {
        return renderPlainText(createStatementData(invoice, plays));
    }

    private StatementData createStatementData(Invoice invoice, Map<String, Play> plays) {
        return new StatementData(invoice, plays);
    }

    private String renderPlainText(StatementData statementData) {
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

    private String renderHtml(StatementData statementData) {
        String result = String.format("<h1>Statement for %s\n<h1>", statementData.getCustomer());
        result += "<table>\n";
        result += "<tr><th>연극</th><th><좌석 수></th><th>금액</th></tr>";

        for (Performance performance : statementData.getPerformances()) {
            result += String.format("  <tr><td>%s: </td><td>%s </td> <td>(%s seats)</td></tr>\n", statementData.playFor(performance).name(), usd(statementData.amountFor(performance)), performance.audience());
        }
        result += "</table>\n";

        result += String.format("<p>Amount owed is <em> %s </em></p>\n", usd(statementData.totalAmount(statementData)));
        result += String.format("<p>You earned <em> %s </em> credits </p>\n", statementData.totalVolumeCredits(statementData));
        return result;
    }

}
