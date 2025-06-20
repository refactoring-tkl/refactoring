package com.tkl.refactoring.chapter12.ch12_10;

public class Bird {
	private String name;
	private String plumage;

	public Bird(String name, String plumage) {
		this.name = name;
		this.plumage = plumage;
	}

	public String getName() {
		return name;
	}

	public String getPlumage() {
		return plumage != null ? plumage : "보통이다";
	}

	public Integer getAirSpeedVelocity() {
		return null;
	}

	static class EuropeanSwallow extends Bird {
		public EuropeanSwallow(String name, String plumage) {
			super(name, plumage);
		}

		@Override
		public Integer getAirSpeedVelocity() {
			return 35;
		}
	}
}
