package com.tkl.refactoring.chapter6;

public class _04InlineVariable {

	public int add(int a, int b) {
		int sum = a + b;
		return sum;
	}

	public int subtract(int a, int b) {
		int difference = a - b;
		return difference;
	}

	public int addAfter(int a, int b) {
		return a + b;
	}

	public int subtractAfter(int a, int b) {
		return a - b;
	}
}
