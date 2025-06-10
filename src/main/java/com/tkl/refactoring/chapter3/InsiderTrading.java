package com.tkl.refactoring.chapter3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

// 3.19 내부자 거래
public class InsiderTrading {
//	public class FacilityLoginAuthorizationService {
	// 수정 사항
	// (1) [FacilityMemberService 추가] facilityMemberRepository, facilityMemberConverter, facilityService (쿠팡 캐시 멤버 조회)
	// (2) [MemberQualifactionCodeService 추가] reserveService (예매번호 기준 회원등급 조회), advanceTicketSearchService (티켓번호 기준 회원등급 조회)
	// (3) [CryptoUtils 추가] encryptUtils, cryptoGetter, crypto (암호화 관련)
//		...
//		@Autowired
//		private EncryptUtils encryptUtils;
//
//		@Autowired
//		private FacilityMemberConverter facilityMemberConverter;
//
//		@Autowired
//		private FacilityMemberRepository facilityMemberRepository;
//
//		@Autowired
//		private AdvanceTicketSearchService advanceTicketSearchService;
//
//		@Autowired
//		private PartnerService partnerService;
//
//		@Autowired
//		@Qualifier("aes256Crypto")
//		private Crypto crypto;
//
//		@Autowired
//		private ReserveService reserveService;
//
//		@Autowired
//		private CryptoGetter cryptoGetter;
//
//		@Autowired
//		private AdvanceTicketService advanceTicketService;
//
//		@Autowired
//		private FacilityLoginPolicyCheck facilityLoginPolicyCheck;
//
//		@Autowired
//		private FacilityService facilityService;
	// ...
	}
