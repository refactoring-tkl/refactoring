package com.tkl.refactoring.chapter3;

// 3.22 데이터 클래스
public class DataClass {
	// @Data 애노테이션 지양
	// @Data 애노테이션은 모든 필드에 대해 getter/setter를 생성하므로, 불필요한 메서드가 생성될 수 있음
	// 따라서, 필요한 필드에 대해서만 getter/setter를 명시적으로 작성하는 것이 좋음
	// ->  @Data 애노테이션을 사용하지 않고, 필요한 필드에 대해서만 getter/setter를 작성
}
