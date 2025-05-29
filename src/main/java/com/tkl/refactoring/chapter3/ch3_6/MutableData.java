package com.tkl.refactoring.chapter3.ch3_6;

import java.util.ArrayList;
import java.util.List;

public class MutableData {
    public static void main(String[] args) {
        // ExternalProduct 사용을 다양하게..;
        // 변수의 값을 갱신하는 코드를 특정 클래스로 빼서 제한했던 구간이있던가..
        // 가변데이터를 가지고와서 추가하는 부분을, 별도 클래스로 만들어서 제어하도록..
        Example example = new Example();
        List<String> mutableData = example.getMutableData();
        mutableData.add("something");

    }

    static class Example {
        List<String> mutableData = new ArrayList<>();

        public List<String> getMutableData() {
            return mutableData;
        }
    }
}
