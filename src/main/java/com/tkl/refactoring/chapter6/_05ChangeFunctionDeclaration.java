package com.tkl.refactoring.chapter6;

import java.time.LocalDate;

public class _05ChangeFunctionDeclaration {

	public static void main(String[] args) {
		_05ChangeFunctionDeclaration service = new _05ChangeFunctionDeclaration();

		int days = service.getDays(1);
		System.out.println("예상 배송일까지: " + days + "일");

		LocalDate estimatedDate = service.calculateDeliveryDate(DeliveryStatus.SHIPPING);
		System.out.println("예상 배송일: " + estimatedDate);
	}

	// status: 0(준비중), 1(배송중), 2(배송완료)
	// 반환값: 배송까지 남은 예상 일수
	public int getDays(int status) {
		if (status == 0) {
			return 5;
		} else if (status == 1) {
			return 2;
		} else if (status == 2) {
			return 0;
		} else {
			throw new IllegalArgumentException("알 수 없는 상태 코드");
		}
	}

	public enum DeliveryStatus {
		PREPARING, SHIPPING, DELIVERED
	}

	public LocalDate calculateDeliveryDate(DeliveryStatus status) {
		int daysToAdd;

		switch (status) {
			case PREPARING:
				daysToAdd = 5;
				break;
			case SHIPPING:
				daysToAdd = 2;
				break;
			case DELIVERED:
				daysToAdd = 0;
				break;
			default:
				throw new IllegalArgumentException("알 수 없는 상태");
		}

		return LocalDate.now().plusDays(daysToAdd);
	}
}

