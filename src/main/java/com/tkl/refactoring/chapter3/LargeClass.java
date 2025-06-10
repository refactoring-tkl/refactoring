package com.tkl.refactoring.chapter3;

// 3.20 거대한 클래스
public class LargeClass {
	// 대상 클래스: Member, Channel ...

	// 수정 예시
	// MyBatis
	// Channel 클래스
//	public class Channel {
//		private ChannelType type;
//		// 기타 필드
//
//		public ChannelType getType() { return type; }
//		// 기타 getter/setter
//	}
//
//	// Channel 추상 클래스 또는 인터페이스
//	public abstract class AbstractChannel {
//		// 공통 메서드
//	}
//
//	// 구체 클래스
//	public class ChannelA extends AbstractChannel { ... }
//	public class ChannelB extends AbstractChannel { ... }
//
//	// 팩토리
//	public class ChannelFactory {
//		public static AbstractChannel create(Channel channel) {
//			switch(channel.getType()) {
//				case A: return new ChannelA(channel);
//				case B: return new ChannelB(channel);
//				default: throw new IllegalArgumentException();
//			}
//		}
//	}

	// JPA
	// ChannelType enum 정의
//	public enum ChannelType {
//		EMAIL, SMS, PUSH
//	}
//
//	import javax.persistence.*;
//
//	@Entity
//	@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//	@DiscriminatorColumn(name = "channel_type")
//	public abstract class Channel {
//		@Id
//		@GeneratedValue(strategy = GenerationType.IDENTITY)
//		private Long id;
//
//		// 공통 필드
//		private String name;
//
//		@Enumerated(EnumType.STRING)
//		@Column(name = "channel_type", insertable = false, updatable = false)
//		private ChannelType type;
//
//		// getter, setter 생략
//	}
//
//	@Entity
//	@DiscriminatorValue("EMAIL")
//	public class EmailChannel extends Channel {
//		private String emailAddress;
//		// getter, setter 생략
//	}
//
//	@Entity
//	@DiscriminatorValue("SMS")
//	public class SmsChannel extends Channel {
//		private String phoneNumber;
//		// getter, setter 생략
//	}
//
//	@Entity
//	@DiscriminatorValue("PUSH")
//	public class PushChannel extends Channel {
//		private String deviceToken;
//		// getter, setter 생략
//	}
}
