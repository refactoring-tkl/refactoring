package com.tkl.refactoring.chapter1.model;

import java.util.List;

public record Invoice(String customer, List<Performance> performances) {

}
