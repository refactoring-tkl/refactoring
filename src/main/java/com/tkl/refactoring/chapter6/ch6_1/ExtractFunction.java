package com.tkl.refactoring.chapter6.ch6_1;

import java.util.List;

import lombok.Getter;

public class ExtractFunction {

	static class Test {
		void printOwing(Invoice invoice) {
			int outstanding = 0;

			System.out.println("*************************");
			System.out.println("**** 고객 채무 ****");
			System.out.println("*************************");

			for (Order order : invoice.getOrders()) {
				outstanding += order.getAmount();
			}

			System.out.println("이름: " + invoice.getName());
			System.out.println("채무액: " + outstanding);
		}
	}

	@Getter
	private static class Invoice {
		private String name;

		public List<Order> getOrders() {
			return null;
		}
	}

	private static class Order {

		public int getAmount() {
			return 0;
		}
	}


	/// ///////////////////////////////////

	static class TestRefactored {
		void printOwing(Invoice invoice) {
			printBanner();

			System.out.println("이름: " + invoice.getName());
			System.out.println("채무액: " + calculateOutstanding(invoice));
		}

		private int calculateOutstanding(Invoice invoice) {
			int result = 0;
			for (Order order : invoice.getOrders()) {
				result += order.getAmount();
			}
			return result;
		}

		private void printBanner() {
			System.out.println("*************************");
			System.out.println("**** 고객 채무 ****");
			System.out.println("*************************");
		}
	}
}
