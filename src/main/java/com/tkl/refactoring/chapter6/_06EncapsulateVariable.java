package com.tkl.refactoring.chapter6;

public class _06EncapsulateVariable {
	public static void main(String[] args) {
		Account account = new Account(0.05);
		System.out.println("Initial rate: " + account.rate);
		account.rate = 0.07;
		System.out.println("account.rate = " + account.rate);


		AccountAfter accountAfter = new AccountAfter(0.1);
		System.out.println("이자율: " + accountAfter.getRate());

		accountAfter.setRate(10);
	}
}


class Account {
	public double rate;

	public Account(double rate) {
		this.rate = rate;
	}
}

class AccountAfter {
	private double rate;

	public AccountAfter(double rate) {
		this.rate = rate;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		if (rate < 0 || rate > 1) {
			throw new IllegalArgumentException("이자율은 0과 1 사이여야 합니다.");
		}
		this.rate = rate;
	}
}
