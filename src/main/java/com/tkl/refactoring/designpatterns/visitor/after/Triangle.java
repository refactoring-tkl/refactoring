package com.tkl.refactoring.designpatterns.visitor.after;

public class Triangle implements Shape {
    @Override
    public void accept(Device device) {
        device.print(this);
    }

//    @Override
//    public void printTo(Phone phone) {
//        System.out.println("print Triangle to phone");
//    }
//
//    @Override
//    public void printTo(Watch watch) {
//        System.out.println("print Triangle to watch");
//    }
}
