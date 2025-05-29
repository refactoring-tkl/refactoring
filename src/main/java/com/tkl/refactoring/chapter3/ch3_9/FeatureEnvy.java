package com.tkl.refactoring.chapter3.ch3_9;

public class FeatureEnvy {
	public static void main(String[] args) {

	}

	static class ExternalProductService {
		CommonDiscount commonDiscount;
		SpecialDiscount1 specialDiscount1;
		SpecialDiscount2 specialDiscount2;

		public int calculateDiscount(int price, ExternalProductServiceType externalProductServiceType) {
			// 일반할인
			// 특별할인1
			// 특별할인2

			price = commonDiscount.applyDiscount(price, externalProductServiceType);
			price = specialDiscount1.applyDiscount(price, externalProductServiceType);
			price = specialDiscount2.applyDiscount(price, externalProductServiceType);

			return price;

		}


	}

	static class CommonDiscount {

		public int applyDiscount(int price, ExternalProductServiceType externalProductServiceType) {
			int discountPrice = 0;
			if (externalProductServiceType == ExternalProductServiceType.LOT) {
				discountPrice = 10;
			} else if (externalProductServiceType == ExternalProductServiceType.SEJ) {
				discountPrice = 20;
			} else if (externalProductServiceType == ExternalProductServiceType.BSA) {
				discountPrice = 30;
			} else if (externalProductServiceType == ExternalProductServiceType.LGA) {
				discountPrice = 40;
			}
			return price - discountPrice;
		}

	}

	static class SpecialDiscount1 {

		public int applyDiscount(int price, ExternalProductServiceType externalProductServiceType) {
			int discountPrice = 0;
			if (externalProductServiceType == ExternalProductServiceType.LOT) {
				discountPrice = 10;
			} else if (externalProductServiceType == ExternalProductServiceType.SEJ) {
				discountPrice = 20;
			} else if (externalProductServiceType == ExternalProductServiceType.BSA) {
				discountPrice = 30;
			} else if (externalProductServiceType == ExternalProductServiceType.LGA) {
				discountPrice = 40;
			}
			return price - discountPrice;
		}
	}

	static class SpecialDiscount2 {
		public int applyDiscount(int price, ExternalProductServiceType externalProductServiceType) {
			int discountPrice = 0;
			if (externalProductServiceType == ExternalProductServiceType.LOT) {
				discountPrice = 10;
			} else if (externalProductServiceType == ExternalProductServiceType.SEJ) {
				discountPrice = 20;
			} else if (externalProductServiceType == ExternalProductServiceType.BSA) {
				discountPrice = 30;
			} else if (externalProductServiceType == ExternalProductServiceType.LGA) {
				discountPrice = 40;
			}
			return price - discountPrice;
		}
	}

	enum ExternalProductServiceType {
		LOT,
		SEJ,
		BSA,
		LGA
	}


}
