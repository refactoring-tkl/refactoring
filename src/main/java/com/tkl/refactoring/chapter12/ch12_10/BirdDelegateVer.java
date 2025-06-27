package com.tkl.refactoring.chapter12.ch12_10;

public class BirdDelegateVer {
	private String name;
	private String plumage;
	private SpeciesDelegate speciesDelegate;

	public BirdDelegateVer(Data data) {
		this.name = data.name;
		this.plumage = data.plumage;
		speciesDelegate = selectSpeciesDelegate(data);
	}

	private SpeciesDelegate selectSpeciesDelegate(Data data) {
		if (data.type.equals("유럽제비")) {
			return new EuropeanSwallowSpeciesDelegate(this, data);
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public String getPlumage() {
		return speciesDelegate.getPlumage();
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

	static class EuropeanSwallowSpeciesDelegate extends SpeciesDelegate {

		public EuropeanSwallowSpeciesDelegate(BirdDelegateVer birdDelegateVer, Data data) {
			super(birdDelegateVer, data);
		}
	}

	static abstract class SpeciesDelegate {
		private final BirdDelegateVer birdDelegateVer;

		public SpeciesDelegate(BirdDelegateVer birdDelegateVer, Data data) {
			this.birdDelegateVer = birdDelegateVer;
		}

		public String getPlumage() {
//			return birdDelegateVer.getPlumage() != null ? birdDelegateVer.plumage : "보통이다"; // getPlumage() 메서드를 호출하면 스택오버플로우발생
			return birdDelegateVer.plumage != null ? birdDelegateVer.plumage : "보통이다"; // 변수를 호출해야함.. 자바에서 이렇게 사용하는건 좀 이상... package-private도 이상..
			// plumage를 위임에서만 쓴다면, SpeciesDelegate에서 plumage를 필드로 가지고있는게 좋을듯..
		}
	}

	static class Data {
		private String name;
		private String plumage;
		private String type;

		public Data(String name, String plumage, String type) {
			this.name = name;
			this.plumage = plumage;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public String getPlumage() {
			return plumage;
		}
	}

	public static void main(String[] args) {
		BirdDelegateVer birdDelegateVer = new BirdDelegateVer(new Data("유럽제비", "아름다운", "유럽제비"));
		System.out.println(birdDelegateVer.getPlumage());
	}
}
