package com.tkl.refactoring.chapter3.ch3_7;

import java.util.HashMap;
import java.util.Map;

public class DivergentChange {
	public static void main(String[] args) {
		// 1. DB 변경 시나리오
		Service service = new Service();
		service.saveSomething(new Something());
	}

	static class Service {
		MysqlRepository mysqlRepository = new MysqlRepository();

		public void saveSomething(Something something) {
			Map<String, String> map = new HashMap<>();
			map.put("id", something.getId());
			map.put("name", something.getName());
			mysqlRepository.save(something);
		}
	}

	static class MysqlRepository {
		public void save(Something something) {

		}
	}

	static class OracleRepository {
		public void save(Something something) {

		}
	}

	private static class Something {
		public String getId() {
			return null;
		}

		public String getName() {
			return null;
		}
	}
}
