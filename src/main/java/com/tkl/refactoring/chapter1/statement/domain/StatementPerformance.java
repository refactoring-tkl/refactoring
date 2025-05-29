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
}
