package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

		for (Statement.StatementPerformance perf : statement.performances()) {
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

	private static class Statement {
		private final String customer;
		private final List<StatementPerformance> performances;
		private final int totalAmount;
		private final int totalVolumeCredits;

		public Statement(Invoice invoice, Map<String, Play> plays) {
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
			return performances.stream()
								  .mapToInt(StatementPerformance::amount)
								  .sum();
		}

		private int calculateTotalVolumeCredits() {
			return performances.stream()
					.mapToInt(StatementPerformance::volumeCredits)
					.sum();
		}

		static class StatementPerformance {
			private final int audience;
			private final Play play;
			private final int amount;
			private final int volumeCredits;

			public StatementPerformance(Performance performance, Play play) {
				this.audience = performance.audience();
				this.play = play;
				this.amount = amountFor();
				this.volumeCredits = volumeCreditsFor();
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

			public int volumeCredits() {
				return volumeCredits;
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

			private int volumeCreditsFor() {
				int result = 0;
				// add volume credits
				result += Math.max(this.audience - 30, 0);
				// add extra credit for every ten comedy attendees
				if ("comedy".equals(this.play.type())) {
					result += this.audience / 5;
				}
				return result;
			}
		}
	}
}
