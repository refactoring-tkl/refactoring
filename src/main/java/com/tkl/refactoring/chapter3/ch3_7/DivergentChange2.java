package com.tkl.refactoring.chapter3.ch3_7;

public class DivergentChange2 {
	public static void main(String[] args) {
		ServiceA serviceA = new ServiceA();
		SharedModel sharedModel1 = serviceA.doSomethingWithSharedModel();

		ServiceB serviceB = new ServiceB(); // 패키지도 아예다름
		SharedModel sharedModel2 = serviceB.doSomethingWithSharedModel();

	}

	static class SharedModel{
		String varForDomain;
		int varForDomain2;
		String varForServiceA;
		String varForServiceB;

		public String getData() {
			return varForDomain + varForDomain2;
		}
	}

	static class ServiceA {
		SharedModelRepository sharedModelRepository = new SharedModelRepository();

		SharedModel doSomethingWithSharedModel() {
			SharedModel sharedModel = sharedModelRepository.find();
			sharedModel.varForServiceA = sharedModel.getData() + "serviceA";

			return sharedModel;
		}
	}

	static class ServiceB {
		SharedModelRepository sharedModelRepository = new SharedModelRepository();
		SharedModel doSomethingWithSharedModel() {
			SharedModel sharedModel = sharedModelRepository.find();
			sharedModel.varForServiceB = sharedModel.getData() + "serviceB";

			return sharedModel;

		}
	}

	static class SharedModelRepository {
		public SharedModel find() {
			return new SharedModel();
		}
	}
}
