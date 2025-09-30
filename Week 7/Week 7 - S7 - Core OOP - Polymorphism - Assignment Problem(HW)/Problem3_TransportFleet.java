abstract class Vehicle {
    abstract void operate();
}
class Bus extends Vehicle {
    @Override void operate() { System.out.println("Bus follows fixed routes and manages passengers."); }
}
class Taxi extends Vehicle {
    @Override void operate() { System.out.println("Taxi calculates fare by distance."); }
}
class Train extends Vehicle {
    @Override void operate() { System.out.println("Train runs on schedule and manages cars."); }
}
class Bike extends Vehicle {
    @Override void operate() { System.out.println("Bike provides eco-friendly short trips."); }
}

public class Problem3_TransportFleet {
    public static void main(String[] args) {
        Vehicle[] fleet = { new Bus(), new Taxi(), new Train(), new Bike() };
        for (Vehicle v : fleet) v.operate();
    }
}
