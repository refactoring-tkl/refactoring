package com.tkl.refactoring.chapter6;

// 6.2 함수 인라인하기
public class InlineFunction {
    // 변경 전
    public static class Invoice {
        private final double amount;

        public Invoice(double amount) {
            this.amount = amount;
        }

        public double getDiscount() {
            return amount > 1000 ? amount * 0.05 : 0;
        }

        public void printFinalAmount() {
            double discount = getDiscount();
            double finalAmount = amount - discount;
            System.out.println("Final amount: " + finalAmount);
        }
    }

    // 변경 후
//    public static class Invoice {
//        private final double amount;
//
//        public Invoice(double amount) {
//            this.amount = amount;
//        }
//
//        public void printFinalAmount() {
//            double discount = amount > 1000 ? amount * 0.05 : 0;
//            double finalAmount = amount - discount;
//            System.out.println("Final amount: " + finalAmount);
//        }
//    }
}
