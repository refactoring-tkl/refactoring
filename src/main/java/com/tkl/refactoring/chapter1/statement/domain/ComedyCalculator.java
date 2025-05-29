package com.tkl.refactoring.chapter1.statement.domain;

class ComedyCalculator extends PerformanceCalculator {

	ComedyCalculator(int audience) {
		super(audience);
	}

	@Override
	int amountFor() {
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
