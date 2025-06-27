package com.tkl.refactoring.chapter3.ch3_13;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Loops {
    public static void main(String[] args) {
        List<Performance> performanceList = getPerformanceListFromExternalApi();
        List<ExternalProduct> externalProductList = convertToExternalProductList(performanceList);
    }

    private static List<ExternalProduct> convertToExternalProductList(List<Performance> performanceList) {
        List<ExternalProduct> result = new ArrayList<>();
        for (Performance performance : performanceList) {
            result.add(
                    new ExternalProduct(performance.getPerformanceName(), performance.getHallName(), new ProductDate(performance.getStartDate(), performance.getEndDate())));
        }

        return result;
    }

    private static List<Performance> getPerformanceListFromExternalApi() {
        return Arrays.asList(
                new Performance("1번상품", "소극장", LocalDate.of(2025, 10, 11), LocalDate.of(2025, 10, 15)),
                new Performance("2번상품", "소극장", LocalDate.of(2025, 8, 11), LocalDate.of(2025, 11, 15)),
                new Performance("3번상품", "대극장", LocalDate.of(2025, 11, 11), LocalDate.of(2025, 12, 15))
        );

    }

    @AllArgsConstructor
    @Getter
    static class ExternalProduct {
        private String name;
        private String hallName;
        private ProductDate productDate;
    }

    @AllArgsConstructor
    @Getter
    static class Performance { // 외부 api의 응답값
        private String performanceName;
        private String hallName;
        private LocalDate startDate;
        private LocalDate endDate;
    }

    @AllArgsConstructor
    @Getter
    private static class ProductDate {
        private LocalDate startDate;
        private LocalDate endDate;
    }
}
