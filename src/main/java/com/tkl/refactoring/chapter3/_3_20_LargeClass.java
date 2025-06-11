package com.tkl.refactoring.chapter3;

import java.util.List;
import java.util.Map;

public class _3_20_LargeClass {

	class Order {

		public void createOrder(String customerId, Map<String, Integer> items) {
			System.out.println("Order created for customer: " + customerId);
		}

		public void cancelOrder(String orderId) {
			System.out.println("Order canceled: " + orderId);
		}

		public void processPayment(String orderId, double amount) {
			System.out.println("Payment processed for order: " + orderId + ", amount: " + amount);
		}

		public void sendOrderConfirmation(String orderId, String email) {
			System.out.println("Order confirmation sent to: " + email);
		}

		public List<String> getOrderHistory(String customerId) {
			System.out.println("Order history retrieved for customer: " + customerId);
			return List.of("Order1", "Order2");
		}

		public void validateOrder(String orderId) {
			System.out.println("Order validated: " + orderId);
		}
	}


	/**
	 * ===== After Refactoring =====
	 */
	class Order_After {

		private OrderService orderService = new OrderService();

		private PaymentService paymentService = new PaymentService();

		private NotificationService notificationService = new NotificationService();

		public void createOrder(String customerId, List<String> items) {
			orderService.createOrder(customerId, items);
		}

		public void cancelOrder(String orderId) {
			orderService.cancelOrder(orderId);
		}

		public void processPayment(String orderId, double amount) {
			paymentService.processPayment(orderId, amount);
		}

		public void sendOrderConfirmation(String orderId, String email) {
			notificationService.sendOrderConfirmation(orderId, email);
		}

		public List<String> getOrderHistory(String customerId) {
			return orderService.getOrderHistory(customerId);
		}
	}

	class OrderService {

		public void createOrder(String customerId, List<String> items) {
			System.out.println("Order created for customer: " + customerId);
		}

		public void cancelOrder(String orderId) {
			System.out.println("Order canceled: " + orderId);
		}

		public List<String> getOrderHistory(String customerId) {
			System.out.println("Order history retrieved for customer: " + customerId);
			return List.of("Order1", "Order2");
		}
	}

	class PaymentService {

		public void processPayment(String orderId, double amount) {
			System.out.println("Payment processed for order: " + orderId + ", amount: " + amount);
		}
	}

	class NotificationService {

		public void sendOrderConfirmation(String orderId, String email) {
			System.out.println("Order confirmation sent to: " + email);
		}
	}
}
