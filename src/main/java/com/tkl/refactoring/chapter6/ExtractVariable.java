package com.tkl.refactoring.chapter6;

import java.util.Map;

// 6.3 변수 추출하기
public class ExtractVariable {
    // 변경 전
//    private Member getMemberProcess(Member paycoMember) throws PaycoApiException {
//        Member member = memberService.getMemberByUUID(paycoMember.getPaycoIdno());
//        // DB에서 조회가 되지 않을 경우 회원 가입으로 간주
//        if (member == null) {
//            member = sleepMemberService.getSleepMemberByPaycoIdno(paycoMember.getPaycoIdno());
//            if (member != null) {
//                // 휴면회원일 경우 휴면해제 후 다시 회원정보 리턴
//                sleepMemberService.recoverFromSleep(member);
//                member = memberService.getMemberByUUID(paycoMember.getPaycoIdno());
//            } else {
//                member = new Member();
//                member.setPaycoIdno(paycoMember.getPaycoIdno());
//                member.setMemberQualificationCode("FREE");
//                Map<String, Object> initResults = initMember(member);
//                memberService.insertMember(member);
//                initResultsProcess(initResults, member);
//            }
//        }
//        return member;
//    }

    // 변경 후
//    private Member getMemberProcess(Member paycoMember) throws PaycoApiException {
//        Member member = memberService.getMemberByUUID(paycoMember.getPaycoIdno());
//        if (member != null) {
//            return member;
//        }
//
//        Member sleepMember = sleepMemberService.getSleepMemberByPaycoIdno(paycoMember.getPaycoIdno());
//        if (sleepMember != null) {
//            // 휴면회원일 경우 휴면해제 후 다시 회원정보 리턴
//            sleepMemberService.recoverFromSleep(member);
//            return memberService.getMemberByUUID(paycoMember.getPaycoIdno());
//        }
//
//        // DB에서 조회가 되지 않을 경우 회원 가입으로 간주
//        Member newMember = new Member();
//        newMember.setPaycoIdno(paycoMember.getPaycoIdno());
//        newMember.setMemberQualificationCode("FREE");
//        Map<String, Object> initResults = initMember(newMember);
//        memberService.insertMember(newMember);
//        initResultsProcess(initResults, newMember);
//        return newMember;
//    }
}
