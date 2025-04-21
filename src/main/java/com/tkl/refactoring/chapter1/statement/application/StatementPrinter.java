package com.tkl.refactoring.chapter1.statement.application;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import com.tkl.refactoring.chapter1.Invoice;
import com.tkl.refactoring.chapter1.Play;
import com.tkl.refactoring.chapter1.statement.domain.Statement;
import com.tkl.refactoring.chapter1.statement.domain.StatementPerformance;

public class StatementPrinter {

	public String print(Invoice invoice, Map<String, Play> plays) {
		return renderPlainText(new Statement(invoice, plays));
	}

	public String printHtml(Invoice invoice, Map<String, Play> plays) {
		return renderHtml(new Statement(invoice, plays));
	}


	private String renderHtml(Statement statement) {
		// html 태그 추가
		return null;
	}

	private String renderPlainText(Statement statement) {
		String result = String.format("Statement for %s\n", statement.customer());

		for (StatementPerformance perf : statement.performances()) {
			// print line for this order
			result += String.format("  %s: %s (%s seats)\n", perf.play().name(), usd(perf.amount()), perf.audience());
		}

		result += String.format("Amount owed is %s\n", usd(statement.totalAmount()));
		result += String.format("You earned %s credits\n", statement.totalVolumeCredits());
		return result;
	}

	private static String usd(int thisAmount) {
		return NumberFormat.getCurrencyInstance(Locale.US).format(thisAmount / 100);
	}


}
