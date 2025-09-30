// File: Problem1.java
abstract class Vehicle {
    public abstract void start(); // abstract method

    public void fuelType() { // non-abstract method
        System.out.println("Uses fuel");
    }
}

class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car starts with key");
    }
}

class Bike extends Vehicle {
    @Override
    public void start() {
        System.out.println("Bike starts with kick");
    }
}

public class Problem1 {
    public static void main(String[] args) {
        Vehicle v1 = new Car(); // Vehicle reference to Car
        v1.start();
        v1.fuelType();

        Vehicle v2 = new Bike(); // Vehicle reference to Bike
        v2.start();
        v2.fuelType();
    }
}
