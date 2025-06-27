package com.tkl.refactoring.chapter3.ch3_20;

public class LargeClazz {
	static class LotteProductService {

		public void saveProduct() throws Exception {
			// 겁나긴 로직들
			// 여기서만 사용하는 repository 또는 service도 필드에 있음

		}

		public void saveSchedule() throws Exception {
			// 겁나긴 로직들
			// 여기서만 사용하는 repository 또는 service도 필드에 있음

		}

		public void saveSchedulePrice() throws Exception {
			// 겁나긴 로직들
			// 여기서만 사용하는 repository 또는 service도 필드에 있음
		}
	}











	static class LotteProductServiceUpgrade {
		ProductRegister productRegister;
		ScheduleRegister scheduleRegister;
		SchedulePriceRegister schedulePriceRegister;

		public void saveProduct() throws Exception {
			productRegister.save();
		}

		public void saveSchedule() throws Exception {
			scheduleRegister.save();
		}

		public void saveSchedulePrice() throws Exception {
			schedulePriceRegister.save();
		}
	}

	private static class ProductRegister {
		public void save() {
		}
	}

	private static class ScheduleRegister {
		public void save() {

		}
	}

	private static class SchedulePriceRegister {
		public void save() {
		}
	}
}
