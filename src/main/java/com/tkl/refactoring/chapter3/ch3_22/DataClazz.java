package com.tkl.refactoring.chapter3.ch3_22;

import lombok.Data;

public class DataClazz {

	/*
	NormalTicketEntranceValidateService

		// 외출요청 + 티켓외출상태 + 개별티켓 => reenter
		// 외출요청 + 티켓외출상태 + 단체티켓 => reenter_group
		if(isAwayTicket(info)){ 																// (1) EntranceStateCode.EntranceStateCode 로 외출 티켓인지 확인
			if (((TicketEntranceInfo) entranceInfo).getBundleIssueYn().equals("Y")) {
				entranceProcessParam.setEntranceProcessType(EntranceProcessType.REENTERED_GROUP);
			} else if (((TicketEntranceInfo) entranceInfo).getBundleIssueYn().equals("N")){
				entranceProcessParam.setEntranceProcessType(EntranceProcessType.REENTERED);     // (2)
			}
			entranceProcessParam.setEntranceRequestType(EntranceRequestType.REENTERED);
		}

	 */

	//...
	static class EntranceProcessParam {
		// ...
		private EntranceProcessType entranceProcessType;

		// ...

	}

	private enum EntranceProcessType {
		ENTER, REENTER
	}
}
