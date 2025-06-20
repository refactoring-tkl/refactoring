package com.tkl.refactoring.chapter4;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class LotteClientServiceTest {

	@Test
	void 응답코드가_500이면_예외가_떨어진다() {
		// given
		LotteClient mock = getMock();
		LotteClientService lotteClientService = new LotteClientService(mock);
		given(mock.getPerformances())
				.willReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

		// when
		// then
		assertThatThrownBy(() -> lotteClientService.getPerformances())
				.isInstanceOf(RuntimeException.class)
				.hasMessage("서버 오류 발생");
	}

	private static LotteClient getMock() {
		return mock(LotteClient.class);
	}

}