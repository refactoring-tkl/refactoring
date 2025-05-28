package com.tkl.refactoring.designpatterns.visitor.after;

public class Rectangle implements Shape {
    @Override
    public void accept(Device device) {
        device.print(this);
    }

//    @Override
//    public void printTo(Phone phone) {
//        System.out.println("print Rectangle to phone");
//    }
//
//    @Override
//    public void printTo(Watch watch) {
//        System.out.println("print Rectangle to watch");
//    }
}
