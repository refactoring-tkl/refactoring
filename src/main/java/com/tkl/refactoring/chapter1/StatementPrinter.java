package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

	public String print(Invoice invoice, Map<String, Play> plays) {
		StatementData statementData = new StatementData(invoice, plays);
		return renderPlainText(statementData);
	}

	private String renderPlainText(StatementData statementData) {
		String result = String.format("Statement for %s\n", statementData.customer());

		for (StatementData.StatementPerformance perf : statementData.performances()) {
			Play play = perf.play();

			// print line for this order
			result += String.format("  %s: %s (%s seats)\n", play.name(), usd(amountFor(perf, play)), perf.audience());
		}

		result += String.format("Amount owed is %s\n", usd(totalAmount(statementData.performances())));
		result += String.format("You earned %s credits\n", totalVolumeCredits(statementData.performances()));
		return result;
	}

	private int totalAmount(List<StatementData.StatementPerformance> performances) {
		int result = 0;
		for (StatementData.StatementPerformance perf : performances) {
			Play play = perf.play();
			int thisAmount = amountFor(perf, play);

			result += thisAmount;
		}
		return result;
	}

	private int totalVolumeCredits(List<StatementData.StatementPerformance> performances) {
		int result = 0;
		for (StatementData.StatementPerformance perf : performances) {
			Play play = perf.play();
			result += volumeCreditsFor(perf, play);
		}
		return result;
	}

	private static String usd(int thisAmount) {
		return NumberFormat.getCurrencyInstance(Locale.US).format(thisAmount / 100);
	}

	private static int volumeCreditsFor(StatementData.StatementPerformance perf, Play play) {
		int result = 0;
		// add volume credits
		result += Math.max(perf.audience() - 30, 0);
		// add extra credit for every ten comedy attendees
		if ("comedy".equals(play.type())) {
			result += perf.audience() / 5;
		}
		return result;
	}

	private int amountFor(StatementData.StatementPerformance perf, Play play) {
		int result;
		switch (play.type()) {
			case "tragedy":
				result = 40000;
				if (perf.audience() > 30) {
					result += 1000 * (perf.audience() - 30);
				}
				break;
			case "comedy":
				result = 30000;
				if (perf.audience() > 20) {
					result += 10000 + 500 * (perf.audience() - 20);
				}
				result += 300 * perf.audience();
				break;
			default:
				throw new Error("unknown type: ${play.type}");
		}
		return result;
	}

	private static class StatementData {
		private final String customer;
		private final List<StatementPerformance> performances;

		public StatementData(Invoice invoice, Map<String, Play> plays) {
			this.customer = invoice.customer();
			this.performances = invoice.performances().stream()
									   .map(performance -> new StatementPerformance(performance, plays.get(
													performance.playID())))
									   .toList();
		}

		public String customer() {
			return customer;
		}

		public List<StatementPerformance> performances() {
			return performances;
		}

		static class StatementPerformance {
			private final int audience;
			private final Play play;

			public StatementPerformance(Performance performance, Play play) {
				this.audience = performance.audience();
				this.play = play;
			}

			public int audience() {
				return audience;
			}

			public Play play() {
				return play;
			}
		}
	}
}
