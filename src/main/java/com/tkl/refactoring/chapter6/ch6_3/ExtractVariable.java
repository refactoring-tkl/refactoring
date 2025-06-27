package com.tkl.refactoring.chapter6.ch6_3;

public class ExtractVariable {
	static double calculatePrice(Order order) {
		return (order.getQuantity() * order.getItemPrice())
					   - Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05
					   + Math.min(order.getQuantity() * order.getItemPrice() * 0.1, 100);
	}

	private static class Order {
		public double getQuantity() {
			return 0;
		}

		public double getItemPrice() {
			return 0;
		}
	}



	/// /////////////////////////////////
	static double calculatePriceRefactored(Order order) {
		double basePrice = order.getQuantity() * order.getItemPrice();
		double quantityDiscount = Math.max(0, order.getQuantity() - 500) * order.getItemPrice() * 0.05;
		double shippingCost = Math.min(basePrice * 0.1, 100);
		return basePrice - quantityDiscount + shippingCost;
	}
}
