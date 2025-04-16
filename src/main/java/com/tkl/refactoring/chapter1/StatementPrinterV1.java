package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StatementPrinterV1 {

    public String print(Invoice invoice, Map<String, Play> plays) {
        String result = String.format("Statement for %s\n", invoice.customer());
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance performance : invoice.performances()) {
            result += String.format("  %s: %s (%s seats)\n", playFor(performance, plays).name(), frmt.format(amountFor(performance, plays) / 100), performance.audience());
        }

        result += String.format("Amount owed is %s\n", frmt.format(totalAmount(invoice, plays) / 100));
        result += String.format("You earned %s credits\n", totalVolumeCredits(invoice, plays));
        return result;
    }

    private int totalAmount(Invoice invoice, Map<String, Play> plays) {
        int result = 0;
        for (Performance performance : invoice.performances()) {
            result += amountFor(performance, plays);
        }
        return result;
    }

    private int totalVolumeCredits(Invoice invoice, Map<String, Play> plays) {
        int result = 0;
        for (Performance performance : invoice.performances()) {
            result += volumeCreditsFor(performance, plays);
        }
        return result;
    }

    private int volumeCreditsFor(Performance performance, Map<String, Play> plays) {
        int result = 0;
        result += Math.max(performance.audience() - 30, 0);
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
