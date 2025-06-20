package com.tkl.refactoring.chapter4;

import java.util.List;

import org.springframework.http.ResponseEntity;

public class LotteClientService {
	private LotteClient lotteClient;

	public LotteClientService(LotteClient lotteClient) {
		this.lotteClient = lotteClient;
	}

	public List<LotteClient.Performance> getPerformances() {
		ResponseEntity<List<LotteClient.Performance>> exchange = lotteClient.getPerformances();

		if (exchange.getStatusCode().is5xxServerError()) {
			throw new RuntimeException("서버 오류 발생");
		}

		if (exchange.getStatusCode().is2xxSuccessful()) {
			return exchange.getBody();
		}

		return null;
	}
}
