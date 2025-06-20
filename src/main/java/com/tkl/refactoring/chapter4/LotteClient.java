package com.tkl.refactoring.chapter4;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.NoOpResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class LotteClient {
	private RestTemplate restTemplate;
	private String baseUrl = "https://api.lotte.com";

	public LotteClient(String baseUrl) {
		restTemplate = new RestTemplateBuilder().errorHandler(new NoOpResponseErrorHandler()).build(); // internal server error시 RestTemplate 내부에서예외던지는게 디폴트..
		this.baseUrl = baseUrl;
	}

	public List<Performance> getPerformances() {
		RequestEntity<Void> requestEntity = RequestEntity.get(baseUrl + "/performances")
														 .accept(MediaType.APPLICATION_JSON)
														 .build();
		ResponseEntity<List<Performance>> exchange = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>() {
		});

		if (exchange.getStatusCode().is5xxServerError()) {
			throw new RuntimeException("서버 오류 발생");
		}

		if (exchange.getStatusCode().is2xxSuccessful()) {
			return exchange.getBody();
		}

		return null;
	}

	@AllArgsConstructor
	@Getter
	static class Performance {
		private String id;
		private String name;
		private LocalDate startDate;
		private LocalDate endDate;
	}
}
