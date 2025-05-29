package com.tkl.refactoring.chapter3.ch3_4;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class LongParameterList {
	public static void main(String[] args) {
		Domain domain = new Domain("id", "name", 90, "anotherDomainId", LocalDateTime.now().minusDays(1), LocalDateTime.now());
		AnotherDomainRepository anotherDomainRepository = new AnotherDomainRepository();
		AnotherDomain anotherDomain = anotherDomainRepository.find(domain.getAnotherDomainId());
		SmellClazz smellClazz = new SmellClazz();
		smellClazz.longParameterList(domain.getId(), domain.getName(), domain.getAge(), anotherDomain);
	}

	@Getter
	@AllArgsConstructor
	static class Domain {
		private String id;
		private String name;
		private int age;
		private String anotherDomainId;
		private LocalDateTime startDateTime;
		private LocalDateTime endDateTime;
	}

	private static class AnotherDomainRepository {
		public AnotherDomain find(String anotherDomainId) {
			return new AnotherDomain(anotherDomainId);
		}
	}

	@Getter
	@AllArgsConstructor
	private static class AnotherDomain {
		private String anotherDomainId;
	}

	private static class SmellClazz {
		public void longParameterList(String id, String name, int age, AnotherDomain anotherDomain) {
			// do something
		}
	}
}


