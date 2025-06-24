package com.tkl.refactoring.chapter6;

// 6.4 변수 인라인하기
public class InlineVariable {
    // 변경 전
//    public ValidationResult validate(ValidationParam param) throws Exception {
//        ValidationResult validationResult = new ValidationResult();
//        String accessToken = param.getAccessToken();
//        String selfRedirect = param.getSelfRedirect();
//        // ...
//        try {
//            // Access token 검증
//            validateAccessToken(accessToken);
//            // ...
//            validatePaycoIdno(paycoIdno, selfRedirect, validationResult);
//            // ...
//        } catch (Exception e) {
//            // ...
//        }
//        // ...
//    }

    // 변경 후
//    public ValidationResult validate(ValidationParam param) throws Exception {
//        ValidationResult validationResult = new ValidationResult();
//        // ...
//        try {
//            // Access token 검증
//            validateAccessToken(param.getAccessToken());
//            // ...
//            validatePaycoIdno(paycoIdno, param.getSelfRedirect(), validationResult);
//            // ...
//        } catch (Exception e) {
//            // ...
//        }
//        // ...
//    }
}
