package com.tkl.refactoring.chapter6;

import lombok.AllArgsConstructor;

public class _01ExtractFunction {
	private UserRepository userRepository = new UserRepository();

	public String register(String username, String password, String email) {
		if (userRepository.existsByUsername(username))
			return "Username already exists";

		if (password.length() < 8)
			return "Password must be at least 8 characters long";

		if (!email.contains("@"))
			return "Email must be valid";

		User user = new User(username, password, email);
		userRepository.save(user);

		return "Registration successful";
	}





	public String registerAfter(String username, String password, String email) {
		if (isUsernameTaken(username))
			return "Username already exists";

		if (isPasswordTooShort(password))
			return "Password must be at least 8 characters long";

		if (isEmailInvalid(email))
			return "Email must be valid";

		createUser(username, password, email);

		return "Registration successful";
	}

	private boolean isUsernameTaken(String username) {
		return userRepository.existsByUsername(username);
	}

	private boolean isPasswordTooShort(String password) {
		return password.length() < 8;
	}

	private boolean isEmailInvalid(String email) {
		return !email.contains("@");
	}

	private void createUser(String username, String password, String email) {
		userRepository.save(new User(username, password, email));
	}

}

class UserRepository {
	public boolean existsByUsername(String username) {
		return false;
	}

	public void save(User user) {
	}
}
@AllArgsConstructor
class User {
	String username;
	String password;
	String email;
}
