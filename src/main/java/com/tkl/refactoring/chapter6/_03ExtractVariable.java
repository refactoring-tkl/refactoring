package com.tkl.refactoring.chapter6;

import lombok.Getter;

public class _03ExtractVariable {

	public double calculateBillingAmount(Customer customer) {

		// 기본 요금 * 할인율(최소 5%) + 세금(10%)
		return (customer.getUsageHours() * customer.getUnitRate())
			   * (1 - (customer.getDiscountRate() > 0.05 ? customer.getDiscountRate() : 0.05))
			   + customer.getUsageHours() * customer.getUnitRate() * 0.1;
	}


	public double calculateBillingAmountAfter(Customer customer) {
		double usageCharge = customer.getUsageHours() * customer.getUnitRate();
		double discountRate = customer.getDiscountRate() > 0.05 ? customer.getDiscountRate() : 0.05;

		double discountedAmount = usageCharge * (1 - discountRate);
		double tax = usageCharge * 0.1;

		return discountedAmount + tax;
	}

}

@Getter
class Customer {
	private double usageHours;
	private double unitRate;
	private double discountRate;
}
