package com.tkl.refactoring.chapter4;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

class LotteClientTest {

	static MockWebServer mockWebServer;

	@Test
	void json_deserialize를_통해_응답객체를_만든다() {
		// given
		mockWebServer.enqueue(new MockResponse()
									  .setResponseCode(HttpStatus.OK.value())
									  .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
									  .setBody(
											  """
											  [
												  {
													"id":"1",
													"name":"Performance 1",
													"startDate":"2023-01-01",
													"endDate":"2023-01-10"
												  },
												  {
													"id":"2",
													"name":"Performance 2",
													"startDate":"2023-02-01",
													"endDate":"2023-02-10"
												  }
											  ]
											  """
											  )
									  );

		String baseUrl = "http://localhost:" + mockWebServer.getPort();
		LotteClient lotteClient = new LotteClient(baseUrl);

		// when
		List<LotteClient.Performance> performances = lotteClient.getPerformances();

		// then
		assertThat(performances).hasSize(2)
				.anySatisfy(performance -> {
					assertThat(performance.getId()).isEqualTo("1");
					assertThat(performance.getName()).isEqualTo("Performance 1");
					assertThat(performance.getStartDate()).isEqualTo("2023-01-01");
					assertThat(performance.getEndDate()).isEqualTo("2023-01-10");
				})
				.anySatisfy(performance -> {
					assertThat(performance.getId()).isEqualTo("2");
					assertThat(performance.getName()).isEqualTo("Performance 2");
					assertThat(performance.getStartDate()).isEqualTo("2023-02-01");
					assertThat(performance.getEndDate()).isEqualTo("2023-02-10");
				});

	}

	@Test
	void 응답코드가_500이면_예외가_떨어진다() {
		// given
		mockWebServer.enqueue(new MockResponse()
									  .setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
									  .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
									  .setBody(
											  """
											  [
												  {
													"id":"1",
													"name":"Performance 1",
													"startDate":"2023-01-01",
													"endDate":"2023-01-10"
												  },
												  {
													"id":"2",
													"name":"Performance 2",
													"startDate":"2023-02-01",
													"endDate":"2023-02-10"
												  }
											  ]
											  """
											  )
							 );

		String baseUrl = "http://localhost:" + mockWebServer.getPort();
		LotteClient lotteClient = new LotteClient(baseUrl);

		// when
		// then
		assertThatThrownBy(() -> lotteClient.getPerformances())
				.isInstanceOf(RuntimeException.class)
				.hasMessage("서버 오류 발생");

	}

	@BeforeAll
	static void setUp() throws IOException {
		mockWebServer = new MockWebServer();
		mockWebServer.start();
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockWebServer.shutdown();
	}


}