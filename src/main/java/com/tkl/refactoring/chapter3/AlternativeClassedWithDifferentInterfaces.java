package com.tkl.refactoring.chapter3;

// 3.21 서로 다른 인터페이스의 대안 클래스들
public class AlternativeClassedWithDifferentInterfaces {
	// KovoApiClient, KblCrmApiClient, WkblApiClient...
	// API Client의 인터페이스를 공통으로 추출 + Connection Pool 사용 고려
	// +) Connection Pool 사용시 트래픽이 많은 경우 사용 고려 (Pool Size, ... 의 경우는 테스트 후 조정 필요)

	// 수정 사항 예시 (문법 정확 하지 않음)
	// Interface 추가
	// Interface ApiClient<T extends ResultWrapper> {
	//   T call(String method, Object param);
	// }

	// public class ApiClient<T extends ResultWrapper> implements ApiClient<T extends ResultWrapper> {
	//   private String host;
	//   private String uri;
	//   public ApiClient(String host, String uri) {
	//     this.host = host;
	//     this.uri = uri;
	//   }
	//   @Override
	//   T call(String method, Object param) {
	//     // API 호출 로직
	//   }
	// }

	// public class KovoApiClient extends ApiClient<KovoResultWrapper> {
	//   public KovoApiClient(String host, String uri) {
	//     super(host, uri);
	//   }
	//   @Override
	//   KovoResultWrapper call(String method, Object param) {
	//     // Kovo API 호출 로직
	//   }
	// }

}
