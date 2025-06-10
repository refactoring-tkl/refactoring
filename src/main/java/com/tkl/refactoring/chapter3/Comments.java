package com.tkl.refactoring.chapter3;

import java.util.Map;

// 3.24 주석
public class Comments {
	// LoginValidator.getMemberProcess
//	private Member getMemberProcess(Member paycoMember) throws PaycoApiException {
//		Member member = memberService.getMemberByUUID(paycoMember.getPaycoIdno());
//		// DB에서 조회가 되지 않을 경우 회원 가입으로 간주
//		if (member == null) {
//			member = sleepMemberService.getSleepMemberByPaycoIdno(paycoMember.getPaycoIdno());
//			if (member != null) {
//				// 휴면회원일 경우 휴면해제 후 다시 회원정보 리턴
//				sleepMemberService.recoverFromSleep(member);
//				member = memberService.getMemberByUUID(paycoMember.getPaycoIdno());
//			} else {
//				member = new Member();
//				member.setPaycoIdno(paycoMember.getPaycoIdno());
//				member.setMemberQualificationCode("FREE");
//				Map<String, Object> initResults = initMember(member);
//				memberService.insertMember(member);
//				initResultsProcess(initResults, member);
//			}
//		}
//		return member;
//	}

	// 수정 코드
//	private Member getMemberProcess(Member paycoMember) throws PaycoApiException {
//		Member member = memberService.getMemberByUUID(paycoMember.getPaycoIdno());
//		if (isMember(member)) {
//			return member;
//		}
//		Member sleepMember = sleepMemberService.getSleepMemberByPaycoIdno(paycoMember.getPaycoIdno());
//		if (isMember(sleepMember)) {
//			sleepMemberService.recoverFromSleep(sleepMember);
//			return memberService.getMemberByUUID(paycoMember.getPaycoIdno());
//		}
//		return registerMember(paycoMember);
//	}
//
//	private Member registerMember(Member paycoMember) {
//		Member member = new Member();
//		member.setPaycoIdno(paycoMember.getPaycoIdno());
//		member.setMemberQualificationCode("FREE");
//		Map<String, Object> initResults = initMember(member);
//		memberService.insertMember(member);
//		initResultsProcess(initResults, member);
//		return member;
//	}
//
//	private boolean isMember(Member member) {
//		return member != null;
//	}
}
