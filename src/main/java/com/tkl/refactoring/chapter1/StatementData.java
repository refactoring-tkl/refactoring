package com.tkl.refactoring.chapter1;

import java.util.List;

public record StatementData(String customer, List<EnrichedPerformance> enrichedPerformances, int totalAmounts, int totalVolumeCredits) {
}
