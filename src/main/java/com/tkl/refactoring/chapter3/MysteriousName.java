package com.tkl.refactoring.chapter3;

public class MysteriousName {
	// pcweb > FacilityLoginController
	private static final String SSO_DELIMETER = "!@#";

	private String makeSSOHashData(String partnerNo, String memberNo, String tid, String timestamp) {
		String combined = partnerNo + SSO_DELIMETER + memberNo + SSO_DELIMETER + tid + SSO_DELIMETER + timestamp;
		String md5 = md5(combined);
		return md5;
	}

	private static String md5(String input) {
		// execute md5 hashing...
		String md5Hash = input;
		return md5Hash;
	}
}
