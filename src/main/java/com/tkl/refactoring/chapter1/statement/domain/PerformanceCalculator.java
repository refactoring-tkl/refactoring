package com.tkl.refactoring.chapter1.statement.domain;

abstract class PerformanceCalculator {
	protected final int audience;

	PerformanceCalculator(int audience) {
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
