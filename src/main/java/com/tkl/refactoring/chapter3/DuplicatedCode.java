package com.tkl.refactoring.chapter3;

public class DuplicatedCode {
	// pcweb > FacilityLoginController
	private static final String SSO_DELIMETER = "!@#";

	private String makeSSOCacheKey(String partnerNo, String memberNo, String tid) {
		String ssoKey = partnerNo + SSO_DELIMETER + memberNo + SSO_DELIMETER + tid;
		return "RedisId.SSO" + "RedisId.SSO" + ssoKey + "S3OperationType.VALUE";
	}

	private String makeSSOHashData(String partnerNo, String memberNo, String tid, String timestamp) {
		String combined = partnerNo + SSO_DELIMETER + memberNo + SSO_DELIMETER + tid + SSO_DELIMETER + timestamp;
		return md5(combined);
	}

	private static String md5(String input) {
		// execute md5 hashing...
		String md5Hash = input;
		return md5Hash;
	}
}
