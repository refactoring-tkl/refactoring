package com.tkl.refactoring.chapter1.statement.domain;

import com.tkl.refactoring.chapter1.Performance;
import com.tkl.refactoring.chapter1.Play;

public class StatementPerformance {
	private final int audience;
	private final Play play;
	private final int amount;
	private final int volumeCredits;

	public StatementPerformance(Performance performance, Play play) {
		this.audience = performance.audience();
		this.play = play;
		PerformanceCalculator performanceCalculator = PerformanceCalculatorFactory.create(audience, play);
		this.amount = performanceCalculator.amountFor();
		this.volumeCredits = performanceCalculator.volumeCreditsFor();
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

	static abstract class PerformanceCalculator {
		protected final int audience;

		public PerformanceCalculator(int audience) {
			this.audience = audience;
		}

		abstract int amountFor();

		final int volumeCreditsFor() {
			return Math.max(this.audience - 30, 0) + extraVolumeCreditsFor();
		}

		protected int extraVolumeCreditsFor() {
			return 0;
		}
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

		@Override
		protected int extraVolumeCreditsFor() {
			return this.audience / 5;
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
