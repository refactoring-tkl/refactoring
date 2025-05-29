package com.tkl.refactoring.chapter3.ch3_11;

import lombok.AllArgsConstructor;

public class PrimitiveObsession {
    public static void main(String[] args) {
        new Product("공연", "2025-05-10", "2025-05-13");
    }

    @AllArgsConstructor
    static class Product {
        private String name;
        private String startDate;
        private String endDate;
    }
}







/*
String startDate -> LocalDate -> startDate, endDate를 ProductDate 값 객체로
 */
