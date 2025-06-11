package com.tkl.refactoring.chapter1;

import com.tkl.refactoring.chapter1.model.StatementData;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        return renderPlainText(createStatementData(invoice, plays));
    }

    private StatementData createStatementData(Invoice invoice, Map<String, Play> plays) {
        return new StatementData(invoice, plays);
    }

    private String renderPlainText(StatementData statementData) {
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", statementData.getCustomer()));

        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("  %s: %s (%s seats)\n", statementData.playFor(performance).name(), usd(statementData.amountFor(performance)), performance.audience()));
        }

        result.append(String.format("Amount owed is %s\n", usd(statementData.totalAmount(statementData))));
        result.append(String.format("You earned %s credits\n", statementData.totalVolumeCredits(statementData)));
        return result.toString();
    }

    private String usd(int amount) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(amount / 100);
    }

    private String renderHtml(StatementData statementData) {
        StringBuilder result = new StringBuilder(String.format("<h1>Statement for %s\n<h1>", statementData.getCustomer()));
        result.append("<table>\n");
        result.append("<tr><th>연극</th><th><좌석 수></th><th>금액</th></tr>");

        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("  <tr><td>%s: </td><td>%s </td> <td>(%s seats)</td></tr>\n", statementData.playFor(performance).name(), usd(statementData.amountFor(performance)), performance.audience()));
        }
        result.append("</table>\n");

        result.append(String.format("<p>Amount owed is <em> %s </em></p>\n", usd(statementData.totalAmount(statementData))));
        result.append(String.format("<p>You earned <em> %s </em> credits </p>\n", statementData.totalVolumeCredits(statementData)));
        return result.toString();
    }

}
