package com.tkl.refactoring.chapter3;

public class _3_23_RefusedBequest {
}

class Person {
	public void eat() {

	}
	public void walk() {

	}
	public void teach() {

	}
	public void study() {

	}
}
class Student extends Person {
	@Override
	public void study() {
		System.out.println("student is studying");
	}
}
class Teacher extends Person {
	@Override
	public void teach() {
		System.out.println("teacher is teaching");
	}
}

/**
 * ===== after refactoring =====
 */

interface Role {
	void perform();
}
class StudentRole implements Role {
	@Override
	public void perform() {
		System.out.println("student is studying");
	}
}
class TeacherRole implements Role {
	@Override
	public void perform() {
		System.out.println("teacher is teaching");
	}
}
class Person_After {
	private final Role role;

	public Person_After(Role role) {
		this.role = role;
	}
	public void eat() {

	}
	public void walk() {

	}
	public void perform() {
		role.perform();
	}
}
