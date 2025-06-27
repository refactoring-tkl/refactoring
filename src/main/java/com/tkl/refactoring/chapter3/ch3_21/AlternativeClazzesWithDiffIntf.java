package com.tkl.refactoring.chapter3.ch3_21;

public class AlternativeClazzesWithDiffIntf {
	static class Account {
		private String accountType;
		private double balance;

		public Account(String accountType, double balance) {
			this.accountType = accountType;
			this.balance = balance;
		}

		public String getAccountType() {
			return accountType;
		}

		public double getBalance() {
			return balance;
		}
	}

	// 대안 클래스 1: AccountFormatter 인터페이스 구현
	interface AccountFormatter {
		String format(Account account);
	}

	class SimpleAccountFormatter implements AccountFormatter {
		public String format(Account account) {
			return account.getAccountType() + ": " + account.getBalance();
		}
	}

	// 대안 클래스 2: AccountPrinter 인터페이스 구현
	interface AccountPrinter {
		void print(Account account);
	}

	class DetailedAccountPrinter implements AccountPrinter {
		public void print(Account account) {
			System.out.println("Account Type: " + account.getAccountType());
			System.out.println("Balance: " + account.getBalance());
		}
	}

	/////////////////////////////

	// 리팩터링 결과
	static class DetailedAccountPrinterRefactoring implements AccountFormatter {

		@Override
		public String format(Account account) {
			StringBuilder builder = new StringBuilder();
			builder.append("Account Type: " + account.getAccountType());
			builder.append("Balance: " + account.getBalance());
			return builder.toString();
		}
	}

	// AccountPrinter 제거

}
