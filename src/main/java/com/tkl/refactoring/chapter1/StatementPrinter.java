package com.tkl.refactoring.chapter1;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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

	private static class Statement { // todo: 별도 도메인 클래스로 분리
		private final String customer;
		private final List<StatementPerformance> performances;
		private final int totalAmount;
		private final int totalVolumeCredits;

		public Statement(Invoice invoice, Map<String, Play> plays) {
			this.customer = invoice.customer();
			this.performances = invoice.performances().stream()
									   .map(performance -> new StatementPerformance(performance, plays.get(
													performance.playID())))
									   .collect(Collectors.toUnmodifiableList());
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
				PerformanceCalculator performanceCalculator = PerformanceCalculatorFactory.create(audience, play);
				this.amount = performanceCalculator.amountFor();
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

	static abstract class PerformanceCalculator {
		protected final int audience;

		public PerformanceCalculator(int audience) {
			this.audience = audience;
		}

		public abstract int amountFor();
	}

	static class TragedyCalculator extends PerformanceCalculator {

		public TragedyCalculator(int audience) {
			super(audience);
		}

		@Override
		public int amountFor() {
			int result = 40000;
			if (super.audience > 30) {
				result += 1000 * (super.audience - 30);
			}
			return result;
		}
	}

	static class ComedyCalculator extends PerformanceCalculator {

		public ComedyCalculator(int audience) {
			super(audience);
		}

		@Override
		public int amountFor() {
			int result = 30000;
			if (super.audience > 20) {
				result += 10000 + 500 * (super.audience - 20);
			}
			result += 300 * super.audience;
			return result;
		}
	}

	private static class PerformanceCalculatorFactory {
		public static PerformanceCalculator create(int audience, Play play) {
            return switch (play.type()) {
                case "tragedy" -> new TragedyCalculator(audience);
                case "comedy" -> new ComedyCalculator(audience);
                default -> throw new Error("unknown type: ${play.type}");
            };
		}
	}
}
