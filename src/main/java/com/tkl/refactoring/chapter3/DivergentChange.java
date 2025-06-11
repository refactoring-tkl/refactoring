package com.tkl.refactoring.chapter3;

import lombok.Data;

public class DivergentChange {

	/**
	 * ===========================
	 * 수 많은 의존 관계 주입 영역
	 * ===========================
	 */


	public void saveProduct() {
		// 상품 정보를 외부 API에서 가져옴
		ExternalProduct externalProduct = ExternalApi.getExternalProduct();
		// 데이터베이스에 상품 정보 저장
		Product product = new Product();
		product.setId(externalProduct.getId());
		product.setName(externalProduct.getName());

		ProductRepository.save(product);
	}

	public void saveSchedule() {
		// 외부 API에서 스케줄 정보를 가져옴
		ExternalSchedule externalSchedule = ExternalApi.getExternalSchedule();
		// 기존 스케쥴 비활성화
		disableAllSchedules();
		// 새로운 스케쥴 저장
		Schedule schedule = new Schedule();
		schedule.setId(externalSchedule.getId());
		schedule.setDate(externalSchedule.getDate());

		ScheduleRepository.save(schedule);
	}

	private void disableAllSchedules() {

	}

	public void saveSchedulePrice() {
		// 외부 api에서 요금표 정보를 가져옴

		// 기존 요금표 비활성화

		// 새로운 요금표 저장
	}

	public void saveHallInfo() {
		// 외부 API에서 공연장 정보를 가져옴

		// 공연장 정보를 저장
	}

	@Data
	private class ExternalProduct {
		String id;
		String name;
	}

	@Data
	private class Product {
		String id;
		String name;
	}

	private class ExternalApi {
		public static ExternalProduct getExternalProduct() {
			return null;
		}

		public static ExternalSchedule getExternalSchedule() {
			return null;
		}
	}

	private class ProductRepository {
		public static void save(Product product) {

		}
	}

	@Data
	private class ExternalSchedule {
		String id;
		String date;
	}

	@Data
	private class Schedule {
		String id;
		String date;
	}

	private static class ScheduleRepository {
		public static void save(Schedule schedule) {

		}
	}
}