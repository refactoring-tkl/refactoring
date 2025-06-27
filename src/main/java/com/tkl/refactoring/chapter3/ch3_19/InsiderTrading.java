package com.tkl.refactoring.chapter3.ch3_19;

import java.util.List;
import java.util.stream.Collectors;

public class InsiderTrading {

	static class EntranceAppSellerLoginMemberServiceFacade {
		private CounterService counterService;

		public void useCounter(Integer counterId, EntranceAppSellerLoginMember member, boolean isOverwrite) {
			Counter counter = counterService.get(counterId);
			if (counter == null) {
				throw new RuntimeException("카운터없음");
			}
			validateCounter(counter, member);

			// ...
		}

		public void validateCounter(Counter counter, EntranceAppSellerLoginMember member) {
			validateLoginPartnerOfMember(member, counter.getPartnerNo().toString());
			validateCounterNoOfMember(member, counter.getCounterNo().toString());
		}

		private void validateCounterNoOfMember(EntranceAppSellerLoginMember member, String counterNo) {
			List<Integer>
					availableCounterNoList = counterService.getOrderedSellerCounter(member.getLoginPartnerNo(), member.getSellerAuthorityLevelCode())
														   .stream()
														   .map(Counter::getCounterNo)
														   .collect(Collectors.toList());

			if (!availableCounterNoList.contains(Integer.parseInt(counterNo))) {
				throw new RuntimeException(String.format("해당 창구를 선택할 수 없는 권한입니다. 요청창구번호 = %s, 사용가능창구번호 = %s", counterNo,
																			  availableCounterNoList));
			}


		}

		private void validateLoginPartnerOfMember(EntranceAppSellerLoginMember member, String partnerNo) {
			Integer loginPartnerNo = member.getLoginPartnerNo();
			if (loginPartnerNo == null) {
				throw new RuntimeException("member must have partnerNo");
			}

			if (!partnerNo.equals(loginPartnerNo.toString())) {
				throw new RuntimeException(String.format("partnerNo not matched. Request PartnerNo = %s, Member PartnerNo = "
														 + "%s", partnerNo, member.getLoginPartnerNo()));
			}
		}
	}


	private static class CounterService {
		public Counter get(Integer counterId) {
			return null;
		}

		public List<Counter> getOrderedSellerCounter(Integer loginPartnerNo, String sellerAuthorityLevelCode) {
			return null;
		}
	}

	private static class Counter {
		public Integer getPartnerNo() {
			return null;
		}

		public Integer getCounterNo() {
			return null;
		}
	}

	private static class EntranceAppSellerLoginMember {
		public Integer getLoginPartnerNo() {
			return null;
		}

		public String getSellerAuthorityLevelCode() {
			// enum 반환
			return null;
		}
	}


	/*
	리팩터링
	Counter 내부에서 모두 validation 하도록 수정..
	 */
}
