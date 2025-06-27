package com.tkl.refactoring.chapter6.ch6_2;

public class InlineFunction {

	static class TestClass {
		void test() {
			ExternalProductSaveCommand givenSaveCommand = new ExternalProductSaveCommand();
			// ...
			validateProduct(givenSaveCommand);
		}

		private void validateProduct(ExternalProductSaveCommand externalProductSaveCommand) {
			doValidateProduct(externalProductSaveCommand.getExternalProductId());
		}

		private void doValidateProduct(String externalProductId) {
//			ExternalProduct externalProduct =
//					externalProductRegisterForTest.findExternalProductFromDb(externalProductId, getExternalProductServiceType());
//			Product product = findProductFromDb(externalProduct.getTicketlinkProductId());
//			assertThat(externalProduct.getTicketlinkProductId()).isEqualTo(product.getProductId());
//			assertThat(externalProduct.getExternalProductName()).isEqualTo(product.getProductName());
//			assertThat(externalProduct.getExternalServiceType().getCode()).isEqualTo(product.getServiceType());
		}


	}
	private static class ExternalProductSaveCommand {
		public String getExternalProductId() {
			return null;
		}
	}






	/// /////////////////////////////////////

	static class TestClassRefactored {
		void test() {
			ExternalProductSaveCommand givenSaveCommand = new ExternalProductSaveCommand();
			// ...
			validateProduct(givenSaveCommand.getExternalProductId());
		}

		private void validateProduct(String externalProductId) {
			//			ExternalProduct externalProduct =
			//					externalProductRegisterForTest.findExternalProductFromDb(externalProductId, getExternalProductServiceType());
			//			Product product = findProductFromDb(externalProduct.getTicketlinkProductId());
			//			assertThat(externalProduct.getTicketlinkProductId()).isEqualTo(product.getProductId());
			//			assertThat(externalProduct.getExternalProductName()).isEqualTo(product.getProductName());
			//			assertThat(externalProduct.getExternalServiceType().getCode()).isEqualTo(product.getServiceType());
		}


	}
}
