package com.tkl.refactoring.designpatterns.visitor.after;

public class Client {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle();

        Device device = new Phone();
//        rectangle.printTo( device);

        rectangle.accept(device);
    }
}
