package com.tkl.refactoring.chapter3;

public class _3_24_Comments {

	public static void main(String[] args) {

		String paymentType = "";
		int amount = 1000;
		// 결제 유형에 따라 다른 메시지를 출력하는 예제
		if ("creditCard".equals(paymentType)) {
			// 1. paymentType이 "creditCard"인 경우
			// 1-1. 결제 금액을 출력
			System.out.println("credit card" + amount);
		} else if ("payco".equals(paymentType)) {
			// 2. paymentType이 "payco"인 경우
			// 2-1. 결제 금액을 출력
			System.out.println("cash" + amount);
		} else if ("samsungPay".equals(paymentType)) {
			// 3. paymentType이 "samsungPay"인 경우
			// 3-1. 결제 금액을 출력
			System.out.println("samsung pay" + amount);
		} else if ("applePay".equals(paymentType)){
			// 4. paymentType이 "applePay"인 경우
			// 4-1. 결제 금액을 출력
			System.out.println("apple pay" + amount);
		} else if ("naverPay".equals(paymentType)){
			// 5. paymentType이 "naverPay"인 경우
			// 5-1. 결제 금액을 출력
			System.out.println("naver pay" + amount);
		} else {
			// 6. 지원하지 않는 결제 유형인 경우
			// 6-1. 예외를 발생시킴
			throw new IllegalArgumentException("Unsupported payment type: " + paymentType);
		}


		// after refactoring
		printMessageByPaymentType(paymentType, amount);

	}

	private static void printMessageByPaymentType(String paymentType, int amount) {
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
