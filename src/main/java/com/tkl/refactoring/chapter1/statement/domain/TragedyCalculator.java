package com.tkl.refactoring.chapter1.statement.domain;

class TragedyCalculator extends PerformanceCalculator {

	TragedyCalculator(int audience) {
		super(audience);
	}

	@Override
	int amountFor() {
		int result = 40000;
		if (super.audience > 30) {
			result += 1000 * (super.audience - 30);
		}
		return result;
	}
}
