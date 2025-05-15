package com.tkl.refactoring.chapter1.model;

public record EnrichedPerformance(Performance performance, Play play, int amount, int volumeCredits) {
}
