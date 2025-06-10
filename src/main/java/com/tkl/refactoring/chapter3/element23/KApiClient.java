package com.tkl.refactoring.chapter3.element23;

import com.tkl.refactoring.chapter3.element21.ApiClient;

public class KApiClient <T extends KResultWrapper> {
	private final ApiClient<T> apiClient;

	public KApiClient(ApiClient<T> apiClient) {
		this.apiClient = apiClient;
	}

	public T callByA(String method, Object param) {
		return apiClient.call(method, param);
	}

	public T callByB(String method, Object param) {
		return apiClient.call(method, param);
	}

	public T callByC(String method, Object param) {
		return apiClient.call(method, param);
	}

}
