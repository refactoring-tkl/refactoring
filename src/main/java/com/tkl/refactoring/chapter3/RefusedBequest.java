package com.tkl.refactoring.chapter3;

// 3.23 상속 포기
public class RefusedBequest {
	// 3.21 서로 다른 인터페이스의 대안 클래스들 예시 코드 중 ApiClient 클래스 메서드에는 call 메서드만 존재
	// 연동사의 API 명세서는 각기 다름
	// 서브 타입으로 나눠서 가도 좋지만, 복잡한 경우 합성(Composition)으로 변경하는 것도 고려
	// 예시 코드: KApiClient, KResultWrapper 클래스
}
