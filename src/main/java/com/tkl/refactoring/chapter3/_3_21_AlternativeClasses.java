package com.tkl.refactoring.chapter3;

public class _3_21_AlternativeClasses {


	class PaymentService {
		public void processPayment(String paymentType, double amount) {
			if ("creditCard".equals(paymentType)) {
				System.out.println("credit card" + amount);
			} else if ("payco".equals(paymentType)) {
				System.out.println("cash" + amount);
			} else if ("samsungPay".equals(paymentType)) {
				System.out.println("samsung pay" + amount);
			} else if ("applePay".equals(paymentType)){
				System.out.println("apple pay" + amount);
			} else if ("naverPay".equals(paymentType)){
				System.out.println("naver pay" + amount);
			} else {
				throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
			}
		}
	}

	/**
	 * ====== After Refactoring =====
	 */

	interface IPaymentService {
		void processPayment(double amount);
	}
	class CreditCardPaymentService implements IPaymentService {
		@Override
		public void processPayment(double amount) {
			System.out.println("credit card" + amount);
		}
	}
	class PaycoPaymentService implements IPaymentService {
		@Override
		public void processPayment(double amount) {
			System.out.println("payco" + amount);
		}
	}
	class SamsungPaymentService implements IPaymentService {
		@Override
		public void processPayment(double amount) {
			System.out.println("samsung pay" + amount);
		}
	}
	class ApplePaymentService implements IPaymentService {
		@Override
		public void processPayment(double amount) {
			System.out.println("apple pay" + amount);
		}
	}
	class NaverPaymentService implements IPaymentService {
		@Override
		public void processPayment(double amount) {
			System.out.println("naver pay" + amount);
		}
	}

}
