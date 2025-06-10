package com.tkl.refactoring.chapter3;

// 3.21 서로 다른 인터페이스의 대안 클래스들
public class AlternativeClassedWithDifferentInterfaces {
	// KovoApiClient, KblCrmApiClient, WkblApiClient...
	// API Client의 인터페이스를 공통으로 추출 + Connection Pool 사용 고려
	// +) Connection Pool 사용시 트래픽이 많은 경우 사용 고려 (Pool Size, ... 의 경우는 테스트 후 조정 필요)
	// element21 패키지에 ApiCallable, ApiClient, ResultWrapper 클래스가 정의되어 있음
}
