package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
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

    public String printHtml(Invoice invoice, Map<String, Play> plays) {
        return renderHtml(new StatementDataCalculator().createStatementData(invoice, plays));
    }

    private String renderHtml(StatementData data) {
        String result = String.format("<h1>Statement for:  %s<h1>\n", data.customer());
        result += "<table>\n";
        result += "<tr><th>Play</th><th>Seats</th><th>Amount</th></tr>";
        for (EnrichedPerformance enrichedPerf : data.enrichedPerformances()) {
            // print line for this order
            result += String.format("   <tr><td>%s</td><td>%s</td><td>%s</td></tr>\n",
                                        enrichedPerf.play().name(),
                                        usd().format(enrichedPerf.amount() / 100),
                                        enrichedPerf.performance().audience());
        }
        result += "</table>\n";
        result += String.format("<p>Amount owed is <em>%s</em></p>\n", usd().format(data.totalAmounts() / 100));
        result += String.format("<p>You earned <em>%s</em> credits<p>\n", data.totalVolumeCredits());
        return result;
    }

    private NumberFormat usd() {
        return NumberFormat.getCurrencyInstance(Locale.US);
    }
}
