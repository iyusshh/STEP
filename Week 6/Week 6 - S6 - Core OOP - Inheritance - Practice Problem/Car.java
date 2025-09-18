// File: Car.java
public class Car extends Vehicle {
    private int numberOfDoors;
    private String fuelType;
    private String transmissionType;

    public Car() {
        super();
        this.numberOfDoors = 4;
        this.fuelType = "Petrol";
        this.transmissionType = "Manual";
        System.out.println("Car default constructor called");
    }

    public Car(String brand, String model, int year, String engineType,
               int numberOfDoors, String fuelType, String transmissionType) {
        super(brand, model, year, engineType);
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
        System.out.println("Car parameterized constructor called");
    }

    @Override
    public void start() {
        super.start();
        System.out.println("Car-specific startup checks complete (doors locked, seatbelts fastened)");
    }

    @Override
    public void displaySpecs() {
        super.displaySpecs();
        System.out.println("Car Specifications:");
        System.out.println("Number of Doors: " + numberOfDoors);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Transmission: " + transmissionType);
    }

    public void openTrunk() {
        System.out.println("Trunk opened");
    }

    public void playRadio() {
        System.out.println("Radio playing music");
    }

    public static void main(String[] args) {
        System.out.println("=== Test 1: Default Constructor ===");
        Car car1 = new Car();
        car1.displaySpecs();
        car1.start();
        car1.stop();
        car1.openTrunk();
        car1.playRadio();
        System.out.println(car1.getVehicleInfo());

        System.out.println("\n=== Test 2: Parameterized Constructor ===");
        Car car2 = new Car("Toyota", "Camry", 2022, "Hybrid", 4, "Hybrid", "Automatic");
        car2.displaySpecs();
        car2.start();
        car2.stop();
        car2.openTrunk();
        car2.playRadio();
        System.out.println(car2.getVehicleInfo());

        System.out.println("\n=== Test 3: Polymorphism ===");
        Vehicle v = new Car("Honda", "Civic", 2023, "Petrol", 4, "Petrol", "Manual");
        v.displaySpecs();
        v.start();
        System.out.println(v.getVehicleInfo());
    }
}
