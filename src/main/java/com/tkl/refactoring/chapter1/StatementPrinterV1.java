package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StatementPrinterV1 {

    public String print(Invoice invoice, Map<String, Play> plays) {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("Statement for %s\n", invoice.customer());

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (Performance perf : invoice.performances()) {
//            Play play = plays.get(perf.playID());

            volumeCredits += volumeCreditsFor(perf);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", playFor(perf).name(), frmt.format(amountFor(perf) / 100), perf.audience());
            totalAmount += amountFor(perf);
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private static int volumeCreditsFor(Performance aPerformance) {
        int result = 0;
        // add volume credits
        result += Math.max(aPerformance.audience() - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(playFor(aPerformance).type())) result += aPerformance.audience() / 5;
        return result;
    }

    private static Play playFor(Performance perf) {
        return ((Map<String, Play>) new HashMap<String, Play>()).get(perf.playID());
    }

    //  1. switch문 함수 추출하기
    private static int amountFor(Performance aPerformance) {
        // 2. 변수의 이름을 더 명확하게 변경 thisAmount -> result
        int result;
        switch (playFor(aPerformance).type()) {
            case "tragedy":
                result = 40000;
                if (aPerformance.audience() > 30) {
                    result += 1000 * (aPerformance.audience() - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (aPerformance.audience() > 20) {
                    result += 10000 + 500 * (aPerformance.audience() - 20);
                }
                result += 300 * aPerformance.audience();
                break;
            default:
                throw new Error("unknown type: ${playFor(aPerformance).type()}");
        }
        return result;
    }

}
