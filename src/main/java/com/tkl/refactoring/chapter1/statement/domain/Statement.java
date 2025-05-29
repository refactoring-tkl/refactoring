package com.tkl.refactoring.chapter1.statement.domain;

import java.util.List;
import java.util.Map;

import com.tkl.refactoring.chapter1.Invoice;
import com.tkl.refactoring.chapter1.Play;

public class Statement {
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
