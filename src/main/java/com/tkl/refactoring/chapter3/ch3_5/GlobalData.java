package com.tkl.refactoring.chapter3.ch3_5;

public class GlobalData {
    public static void main(String[] args) {

    }

    static class GlobalDataExample {
        public String globalInstanceVariable;
        public MutableVariable mutableVariable;

    }

    static class MutableVariable {
        int i = 0;

        void addNum(int num) {
            i += num;
        }
    }
}
