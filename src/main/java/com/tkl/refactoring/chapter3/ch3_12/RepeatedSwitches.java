package com.tkl.refactoring.chapter3.ch3_12;

import lombok.Getter;

import java.time.LocalDateTime;

public class RepeatedSwitches {
    public static void main(String[] args) {
        Ticket ticket = new Ticket(TicketType.CONCERT, 100, 1, LocalDateTime.of(2025, 10, 1, 11, 10));
        double discountedPrice = TicketUtils.calculateDiscountedPrice(ticket);
        LocalDateTime localDateTime = TicketUtils.validDurationDateTime(ticket);
    }

    // 티켓 종류 enum
    enum TicketType { CONCERT, SPORTS, THEATER }

    // 티켓 클래스
    @Getter
    static class Ticket {
        TicketType type;
        double price;
        int seatNumber;
        LocalDateTime startDateTime; // 공연 시간(분)

        Ticket(TicketType type, double price, int seatNumber, LocalDateTime startDateTime) {
            this.type = type;
            this.price = price;
            this.seatNumber = seatNumber;
            this.startDateTime = startDateTime;
        }
    }

    // 티켓 관련 유틸 클래스
    static class TicketUtils {
        // switch 1: 티켓 가격 할인 계산
        public static double calculateDiscountedPrice(Ticket ticket) {
            switch (ticket.type) {
                case CONCERT: return ticket.price * 0.9; // 10% 할인
                case SPORTS: return ticket.price * 0.85; // 15% 할인
                case THEATER: return ticket.price * 0.8; // 20% 할인
                default: throw new IllegalArgumentException();
            }
        }

        // switch 2: 티켓 유효 시간 계산
        public static LocalDateTime validDurationDateTime(Ticket ticket) {
            switch (ticket.type) {
                case CONCERT: return ticket.getStartDateTime().plusMinutes(30); // 공연 후 30분 유효
                case SPORTS: return ticket.getStartDateTime().plusMinutes(15); // 경기 후 15분 유효
                case THEATER: return ticket.getStartDateTime().plusMinutes(20); // 공연 후 20분 유효
                default: throw new IllegalArgumentException();
            }
        }
    }

}
