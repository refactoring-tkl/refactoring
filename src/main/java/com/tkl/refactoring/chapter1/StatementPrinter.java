package com.tkl.refactoring.chapter1;

import com.tkl.refactoring.chapter1.model.EnrichedPerformance;
import com.tkl.refactoring.chapter1.model.Invoice;
import com.tkl.refactoring.chapter1.model.Play;
import com.tkl.refactoring.chapter1.model.StatementData;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {
    public String print(Invoice invoice, Map<String, Play> plays) {
        return renderPlainText(StatementDataBuilder.createStatementData(invoice, plays));
    }

    private String renderPlainText(StatementData data) {
        StringBuilder plainTextBuilder = new StringBuilder();
        plainTextBuilder.append(String.format("Statement for %s\n", data.customer()));
        for (EnrichedPerformance enrichedPerf : data.enrichedPerformances()) {
            // print line for this order
            plainTextBuilder.append(String.format("  %s: %s (%s seats)\n",
                                        enrichedPerf.play().name(),
                                        usd().format(enrichedPerf.amount() / 100),
                                        enrichedPerf.performance().audience()));
        }
        plainTextBuilder.append(String.format("Amount owed is %s\n", usd().format(data.totalAmounts() / 100)))
                        .append(String.format("You earned %s credits\n", data.totalVolumeCredits()));
        return plainTextBuilder.toString();
    }

    public String printHtml(Invoice invoice, Map<String, Play> plays) {
        return renderHtml(StatementDataBuilder.createStatementData(invoice, plays));
    }

    private String renderHtml(StatementData data) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append(String.format("<h1>Statement for:  %s<h1>\n", data.customer()))
                    .append("<table>\n")
                    .append("<tr><th>Play</th><th>Seats</th><th>Amount</th></tr>");
        for (EnrichedPerformance enrichedPerf : data.enrichedPerformances()) {
            // print line for this order
            htmlBuilder.append(String.format("   <tr><td>%s</td><td>%s</td><td>%s</td></tr>\n",
                                        enrichedPerf.play().name(),
                                        usd().format(enrichedPerf.amount() / 100),
                                        enrichedPerf.performance().audience()));
        }
        htmlBuilder.append("</table>\n")
                .append(String.format("<p>Amount owed is <em>%s</em></p>\n", usd().format(data.totalAmounts() / 100)))
                .append(String.format("<p>You earned <em>%s</em> credits<p>\n", data.totalVolumeCredits()));
        return htmlBuilder.toString();
    }

    private NumberFormat usd() {
        return NumberFormat.getCurrencyInstance(Locale.US);
    }
}
