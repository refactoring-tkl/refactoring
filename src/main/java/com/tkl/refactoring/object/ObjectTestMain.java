package com.tkl.refactoring.object;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.tkl.refactoring.object.common.Call;
import com.tkl.refactoring.object.common.Money;
import com.tkl.refactoring.object.phone.AbstractPhone;
import com.tkl.refactoring.object.phone.NightlyDiscountPhone;
import com.tkl.refactoring.object.phone.Phone;
import com.tkl.refactoring.object.phone.RegularAmountDiscountPolicy;
import com.tkl.refactoring.object.phone.TaxPolicy;

public class ObjectTestMain {
	public static void main(String[] args) {
		AbstractPhone phone = new Phone(Money.wons(1000), Duration.ofSeconds(10));
		phone.addExtraPolicy(new TaxPolicy());
		Call call = new Call(LocalDateTime.of(2023, 10, 1, 22, 0)
				, LocalDateTime.of(2023, 10, 1, 23, 0));
		phone.call(call);
		phone.call(call);
		phone.call(call);
		System.out.println(phone.calculateFee());

		AbstractPhone phone2 = new Phone(Money.wons(1000), Duration.ofSeconds(10));
		phone2.call(call);
		phone2.call(call);
		phone2.call(call);
		System.out.println(phone2.calculateFee());

		AbstractPhone phone3 = new Phone(Money.wons(1000), Duration.ofSeconds(10));
		phone3.addExtraPolicies(Arrays.asList(new RegularAmountDiscountPolicy(), new TaxPolicy()));
		phone3.call(call);
		phone3.call(call);
		phone3.call(call);
		System.out.println(phone3.calculateFee());

		Call morningcall = new Call(LocalDateTime.of(2023, 10, 1, 7, 0)
				, LocalDateTime.of(2023, 10, 1, 8, 0));
		AbstractPhone nightlyDiscountPhone = new NightlyDiscountPhone(Money.wons(2000), Money.wons(1000), Duration.ofSeconds(10));
		nightlyDiscountPhone.call(call);
		nightlyDiscountPhone.call(call);
		nightlyDiscountPhone.call(call);
		nightlyDiscountPhone.call(morningcall);
		System.out.println(nightlyDiscountPhone.calculateFee());

		AbstractPhone nightlyDiscountPhone2 = new NightlyDiscountPhone(Money.wons(2000), Money.wons(1000), Duration.ofSeconds(10));
		nightlyDiscountPhone2.addExtraPolicy(new TaxPolicy());
		nightlyDiscountPhone2.call(call);
		nightlyDiscountPhone2.call(call);
		nightlyDiscountPhone2.call(call);
		nightlyDiscountPhone2.call(morningcall);
		System.out.println(nightlyDiscountPhone2.calculateFee());

		AbstractPhone nightlyDiscountPhone3 = new NightlyDiscountPhone(Money.wons(2000), Money.wons(1000), Duration.ofSeconds(10));
		nightlyDiscountPhone3.addExtraPolicies(Arrays.asList(new RegularAmountDiscountPolicy(), new TaxPolicy()));
		nightlyDiscountPhone3.call(call);
		nightlyDiscountPhone3.call(call);
		nightlyDiscountPhone3.call(call);
		nightlyDiscountPhone3.call(morningcall);
		System.out.println(nightlyDiscountPhone3.calculateFee());
	}
}
