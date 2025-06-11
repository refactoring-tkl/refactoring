package com.tkl.refactoring.designpatterns.visitor.after;

public interface Shape {
//    void printTo(Phone phone);
//    void printTo(Watch watch);

    void accept(Device device);
}
