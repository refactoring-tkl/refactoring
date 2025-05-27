package com.tkl.refactoring.chapter3;

public class RepeatedSwitches {
	public String getMainCouponList(String channelCode) {
		switch(channelCode) {
			case "TKL_WEB":
				return "TKL_WEB 쿠폰";
			case "TKL_MOBILE_WEB":
				return "TKL_MOBILE_WEB 쿠폰";
			case "TKL_APP":
				return "TKL_APP 쿠폰";
			case "THEATER_APP":
				return "THEATER_APP 쿠폰";
			default:
				return "기타 쿠폰";
		}
	}
}
