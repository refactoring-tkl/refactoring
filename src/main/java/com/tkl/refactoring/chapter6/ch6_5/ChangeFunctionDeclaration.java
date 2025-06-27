package com.tkl.refactoring.chapter6.ch6_5;

public class ChangeFunctionDeclaration {
	// 코드를 다시 볼때 무슨일을 하는지 "또" 고민하고 싶지않으면 어떤일을 하는지를 잘 정의하라
	static class Smell {
		private CatalogServiceSoap catalogServiceSoap;

		public void saveProduct() {
			// 외부 연동사로부터 상품등록을 위한 데이터를 가져온다
			PerformanceFindCriteria criteria = new PerformanceFindCriteria();
			Performance[] performances = catalogServiceSoap.getPerformace(criteria);
		}
	}








	/// //////////////////////////////////
	static class SmellRefactored {
		private CatalogServiceSoap catalogServiceSoap;

		public void saveProduct() {
			// 외부 연동사로부터 상품등록을 위한 데이터를 가져온다
			Performance[] performances = findPerformancesByLotteApi();
		}

		private Performance[] findPerformancesByLotteApi() {
			PerformanceFindCriteria criteria = new PerformanceFindCriteria();
			return catalogServiceSoap.getPerformace(criteria);
		}
	}

	private static class PerformanceFindCriteria {
	}

	private static class CatalogServiceSoap {
		public Performance[] getPerformace(PerformanceFindCriteria criteria) {
			return null;
		}
	}

	private static class Performance {
	}
}
