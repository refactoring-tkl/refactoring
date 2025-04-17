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
			// print line for this order
			result += String.format("  %s: %s (%s seats)\n", perf.play().name(), usd(amountFor(perf)), perf.audience());
		}

		result += String.format("Amount owed is %s\n", usd(statementData.totalAmount()));
		result += String.format("You earned %s credits\n", statementData.totalVolumeCredits());
		return result;
	}

	private static String usd(int thisAmount) {
		return NumberFormat.getCurrencyInstance(Locale.US).format(thisAmount / 100);
	}

	private int amountFor(StatementData.StatementPerformance perf) {
		int result;
		switch (perf.play().type()) {
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
		private final int totalAmount;
		private final int totalVolumeCredits;

		public StatementData(Invoice invoice, Map<String, Play> plays) {
			this.customer = invoice.customer();
			this.performances = invoice.performances().stream()
									   .map(performance -> new StatementPerformance(performance, plays.get(
													performance.playID())))
									   .toList();
			this.totalAmount = calculateTotalAmount();
			this.totalVolumeCredits = calculateTotalVolumeCredits();
		}

		public String customer() {
			return customer;
		}

		public List<StatementPerformance> performances() {
			return performances;
		}

		public int totalAmount() {
			return totalAmount;
		}

		public int totalVolumeCredits() {
			return totalVolumeCredits;
		}

		private int calculateTotalAmount() {
			int result = 0;
			for (StatementData.StatementPerformance perf : performances) {
				int thisAmount = perf.amount();

				result += thisAmount;
			}
			return result;
		}

		private int calculateTotalVolumeCredits() {
			int result = 0;
			for (StatementData.StatementPerformance perf : performances) {
				result += volumeCreditsFor(perf);
			}
			return result;
		}

		private int volumeCreditsFor(StatementData.StatementPerformance perf) {
			int result = 0;
			// add volume credits
			result += Math.max(perf.audience() - 30, 0);
			// add extra credit for every ten comedy attendees
			if ("comedy".equals(perf.play().type())) {
				result += perf.audience() / 5;
			}
			return result;
		}

		static class StatementPerformance {
			private final int audience;
			private final Play play;
			private final int amount;

			public StatementPerformance(Performance performance, Play play) {
				this.audience = performance.audience();
				this.play = play;
				this.amount = amountFor();
			}

			public int audience() {
				return audience;
			}

			public Play play() {
				return play;
			}

			public int amount() {
				return amount;
			}

			private int amountFor() {
				int result;
				switch (this.play.type()) {
					case "tragedy":
						result = 40000;
						if (this.audience > 30) {
							result += 1000 * (this.audience - 30);
						}
						break;
					case "comedy":
						result = 30000;
						if (this.audience > 20) {
							result += 10000 + 500 * (this.audience - 20);
						}
						result += 300 * this.audience;
						break;
					default:
						throw new Error("unknown type: ${play.type}");
				}
				return result;
			}
		}
	}
}
