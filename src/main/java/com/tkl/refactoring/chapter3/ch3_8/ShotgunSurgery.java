package com.tkl.refactoring.chapter3.ch3_8;

public class ShotgunSurgery {
	public static void main(String[] args) {

	}

	static class ServiceA {
		void doSomething(String name) {
			Model model = new Model();
			model.setName(name);
		}
	}

	static class ServiceB {
		ExternalApi externalApi;
		void doSomething() {
			Model model = new Model();
			String name = externalApi.getName();
			if (name.length() > 10) {
				throw new IllegalArgumentException("이름은 10자 이하로 입력해주세요.");
			}

			model.setName(name);
		}
	}

	static class Model {
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			validateName(name);
			this.name = name;
		}

		private void validateName(String name) {
			if (name.length() > 10) {
				throw new IllegalArgumentException("이름은 10자 이하로 입력해주세요.");
			}
		}
	}

	private static class ExternalApi {
		public String getName() {
			return "hi";
		}
	}
}
