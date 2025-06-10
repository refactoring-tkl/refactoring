package com.tkl.refactoring.chapter3.element21;

public class ApiClient<T extends ResultWrapper> implements ApiCallable<T> {
	private String host;
	private String uri;

	public ApiClient(String host, String uri) {
		this.host = host;
		this.uri = uri;
	}

	@Override
	public T call(String method, Object param) {
		// API 호출 로직 구현
		return null; // 실제 구현에서는 적절한 ResultWrapper 객체를 반환해야 함
	}
}
