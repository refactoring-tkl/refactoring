package com.tkl.refactoring.chapter3.ch3_1;

public class MysteriousName {
	public static void main(String[] args) {
		ExternalProduct externalProduct = new ExternalProduct();

		ExternalProduct savedProgram = findByExternalProductId("externalProductId");
		if (savedProgram == null) {
			externalProduct.setSomething("something1");
			externalProduct.setSomethingMore("something2");
		}

		// ... save 로직
	}

	private static ExternalProduct findByExternalProductId(String externalProductId) {
		return null;
	}

	private static class ExternalProduct {
		public void setSomething(String something1) {

		}

		public void setSomethingMore(String something2) {

		}
	}
}
