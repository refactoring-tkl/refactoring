package com.tkl.refactoring.chapter3.ch3_2;

public class DuplicatedCode {
	public static void main(String[] args) {
		DuplicatedCodeExample duplicatedCodeExample = new DuplicatedCodeExample();
		System.out.println(duplicatedCodeExample.plusOneAndToUppercase("abc"));
		System.out.println(duplicatedCodeExample.plusTwoAndToUppercase("abc"));
	}

	static class DuplicatedCodeExample {
		public String plusOneAndToUppercase(String target) {
			StringBuilder sb = new StringBuilder();

			target += "One";
			for (String s : target.split("")) {
				sb.append(s.toUpperCase());
			}

			return sb.toString();
		}

		public String plusTwoAndToUppercase(String target) {
			target += "Two";

			StringBuilder sb = new StringBuilder();
			for (String s : target.split("")) {
				sb.append(s.toUpperCase());
			}

			return sb.toString();
		}
	}
}
