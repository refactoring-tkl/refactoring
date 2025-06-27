package com.tkl.refactoring.chapter6.ch6_6;

import java.util.List;
import java.util.Map;

import com.tkl.refactoring.chapter1.Invoice;
import com.tkl.refactoring.chapter1.Performance;
import com.tkl.refactoring.chapter1.Play;
import com.tkl.refactoring.chapter1.statement.domain.StatementPerformance;

public class EncapsulateVariable {

	// 변수에 직접접근하는것을 막자
	// 데이터의 유효범위가 넓을수록 더욱 캡슐화해야한다
	// 단순히 접근에 대한 캡슐화뿐 아니라, 그 데이터에 접근하여 변경하는 행위까지 제어할 수 있도록 캡슐화를 해야할지 고려하자
	    // getter에서 복제를 할것인가? setter에서 복제를 할 것인가? 이것은 원본데이터의 링크를 유지해야하느냐에 따라 달라질 수 있다.

	static class Statement {
		private final String customer;
		private final List<Performance> performances;
		// ...

		public Statement(Invoice invoice, Map<String, Play> plays) {
			this.customer = invoice.customer();
			this.performances = invoice.performances();
			// ...
		}

		public String customer() {
			return customer;
		}

		public List<Performance> performances() {
			return performances;
		}

		// ...

	}


	static class StatementRefactored {
		private final String customer;
		private final List<StatementPerformance> performances;
		private final int totalAmount;
		private final int totalVolumeCredits;

		public StatementRefactored(Invoice invoice, Map<String, Play> plays) {
			this.customer = invoice.customer();
			this.performances = invoice.performances().stream()
									   .map(performance -> new StatementPerformance(performance, plays.get(
											   performance.playID())))
									   .toList();
			this.totalAmount = calculateTotalAmount(this.performances);
			this.totalVolumeCredits = calculateTotalVolumeCredits(this.performances);
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

		private int calculateTotalAmount(List<StatementPerformance> performances) {
			return performances.stream()
							   .mapToInt(StatementPerformance::amount)
							   .sum();
		}

		private int calculateTotalVolumeCredits(List<StatementPerformance> performances) {
			return performances.stream()
							   .mapToInt(StatementPerformance::volumeCredits)
							   .sum();
		}

	}
}
