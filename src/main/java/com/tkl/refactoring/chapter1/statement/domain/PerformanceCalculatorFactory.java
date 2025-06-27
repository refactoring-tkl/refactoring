package com.tkl.refactoring.chapter1.statement.domain;

import com.tkl.refactoring.chapter1.Play;

class PerformanceCalculatorFactory {
	static PerformanceCalculator create(int audience, Play play) {
		return switch (play.type()) {
			case "tragedy" -> new TragedyCalculator(audience);
			case "comedy" -> new ComedyCalculator(audience);
			default -> throw new Error("unknown type: ${play.type}");
		};
	}
}
