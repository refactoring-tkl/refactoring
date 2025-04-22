package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {
    public String print(Invoice invoice, Map<String, Play> plays) {
        return renderPlainText(new StatementDataCalculator().createStatementData(invoice, plays));
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

    private NumberFormat usd() {
        return NumberFormat.getCurrencyInstance(Locale.US);
    }
}
