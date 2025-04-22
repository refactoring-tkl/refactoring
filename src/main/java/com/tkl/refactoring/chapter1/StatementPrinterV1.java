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

        public StatementData(Invoice invoice) {
            this.customer = invoice.customer();
            this.performances = invoice.performances();
        }

    }

    public String print(Invoice invoice, Map<String, Play> plays) {
        StatementData statementData = new StatementPrinterV1.StatementData(invoice);
        return renderPlainText(statementData, plays);
    }

    private String renderPlainText(StatementData statementData, Map<String, Play> plays) {
        String result = String.format("Statement for %s\n", statementData.getCustomer());

        for (Performance performance : statementData.getPerformances()) {
            result += String.format("  %s: %s (%s seats)\n", playFor(performance, plays).name(), usd(amountFor(performance, plays)), performance.audience());
        }

        result += String.format("Amount owed is %s\n", usd(totalAmount(statementData, plays)));
        result += String.format("You earned %s credits\n", totalVolumeCredits(statementData, plays));
        return result;
    }

    private String usd(int amount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount / 100);
    }

    private int totalAmount(StatementData statementData, Map<String, Play> plays) {
        int result = 0;
        for (Performance performance : statementData.getPerformances()) {
            result += amountFor(performance, plays);
        }
        return result;
    }

    private int totalVolumeCredits(StatementData statementData, Map<String, Play> plays) {
        int result = 0;
        for (Performance performance : statementData.getPerformances()) {
            result += volumeCreditsFor(performance, plays);
        }
        return result;
    }

    private int volumeCreditsFor(Performance performance, Map<String, Play> plays) {
        int result = 0;
        // add volume credits
        result += Math.max(performance.audience() - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(playFor(performance, plays).type())) result += performance.audience() / 5;
        return result;
    }

    private Play playFor(Performance performance, Map<String, Play> plays) {
        return plays.get(performance.playID());
    }

    //  1. switch문 함수 추출하기
    private int amountFor(Performance performance, Map<String, Play> plays) {
        // 2. 변수의 이름을 더 명확하게 변경 thisAmount -> result
        int result;
        switch (playFor(performance, plays).type()) {
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

}
