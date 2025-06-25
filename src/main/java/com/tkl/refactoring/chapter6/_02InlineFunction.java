package com.tkl.refactoring.chapter6;

public class _02InlineFunction {

	public boolean login(String username, String password) {
		if (!isAuthenticated(username, password))
			return false;

		System.out.println("Login successful for user: " + username);
		return true;
	}

	private boolean isAuthenticated(String username, String password) {
		return "admin".equals(username) && "1234".equals(password);
	}


	public boolean loginAfter(String username, String password) {
		if (!("admin".equals(username) && "1234".equals(password))) {
			return false;
		}

		System.out.println("로그인 성공: " + username);
		return true;
	}
}
