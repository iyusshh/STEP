import java.util.*;

class Vehicle {
    String vehicleId, brand, model, fuelType, currentStatus;
    int year;
    double mileage;
    static int totalVehicles = 0;
    static double fleetValue = 0;
    static String companyName = "SRM Logistics";
    static double totalFuelConsumption = 0;

    Vehicle(String vehicleId, String brand, String model, int year, double mileage, String fuelType) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.currentStatus = "Available";
        totalVehicles++;
    }

    void updateMileage(double newMileage) {
        mileage = newMileage;
        System.out.println("Updated mileage for " + vehicleId + ": " + mileage + " km");
    }

    void scheduleMaintenance() {
        System.out.println("Maintenance scheduled for Vehicle ID: " + vehicleId);
    }

    boolean checkServiceDue() {
        return mileage > 15000;
    }

    double calculateRunningCost(double fuelPricePerLitre, double fuelUsed) {
        totalFuelConsumption += fuelUsed;
        return fuelPricePerLitre * fuelUsed;
    }
}

class Car extends Vehicle {
    int seatingCapacity;

    Car(String vehicleId, String brand, String model, int year, double mileage, String fuelType, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType);
        this.seatingCapacity = seatingCapacity;
    }
}

class Bus extends Vehicle {
    int seatingCapacity;

    Bus(String vehicleId, String brand, String model, int year, double mileage, String fuelType, int seatingCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType);
        this.seatingCapacity = seatingCapacity;
    }
}

class Truck extends Vehicle {
    double loadCapacity;

    Truck(String vehicleId, String brand, String model, int year, double mileage, String fuelType, double loadCapacity) {
        super(vehicleId, brand, model, year, mileage, fuelType);
        this.loadCapacity = loadCapacity;
    }
}

class Driver {
    String driverId, driverName, licenseType;
    Vehicle assignedVehicle;
    int totalTrips = 0;

    Driver(String driverId, String driverName, String licenseType) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.licenseType = licenseType;
    }

    void assignVehicle(Vehicle vehicle) {
        assignedVehicle = vehicle;
        vehicle.currentStatus = "Assigned to " + driverName;
        System.out.println(driverName + " assigned to vehicle: " + vehicle.vehicleId);
    }

    void completeTrip(double fuelUsed) {
        if (assignedVehicle != null) {
            assignedVehicle.mileage += fuelUsed * 10; // Approx mileage increment
            totalTrips++;
            System.out.println(driverName + " completed a trip. Total trips: " + totalTrips);
        } else {
            System.out.println("Driver " + driverName + " has no assigned vehicle.");
        }
    }
}

public class VehicleFleetManagement {
    static ArrayList<Vehicle> fleet = new ArrayList<>();
    static ArrayList<Driver> drivers = new ArrayList<>();

    static void addSampleData() {
        fleet.add(new Car("C1", "Maruti", "Swift", 2022, 5000, "Petrol", 5));
        fleet.add(new Bus("B1", "Volvo", "9400", 2021, 30000, "Diesel", 45));
        fleet.add(new Truck("T1", "Tata", "Xenon", 2020, 45000, "Diesel", 8.5));

        drivers.add(new Driver("D1", "Amit", "LMV"));
        drivers.add(new Driver("D2", "Ravi", "HMV"));
    }

    static void displayFleet() {
        System.out.println("\n---- Vehicle Fleet ----");
        for (Vehicle v : fleet) {
            System.out.println(v.vehicleId + " | " + v.brand + " " + v.model + " | " + v.currentStatus);
        }
    }

    static void assignDriverToVehicle(String driverId, String vehicleId) {
        Driver d = null;
        Vehicle v = null;
        for (Driver dr : drivers)
            if (dr.driverId.equals(driverId)) d = dr;
        for (Vehicle vh : fleet)
            if (vh.vehicleId.equals(vehicleId)) v = vh;
        if (d != null && v != null) d.assignVehicle(v);
        else System.out.println("Invalid Driver or Vehicle ID!");
    }

    static void getFleetUtilization() {
        long assigned = fleet.stream().filter(v -> !v.currentStatus.equals("Available")).count();
        double utilization = ((double) assigned / fleet.size()) * 100;
        System.out.println("Fleet Utilization: " + utilization + "%");
    }

    public static void main(String[] args) {
        addSampleData();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Vehicle Fleet Management ---");
            System.out.println("1. Display Fleet");
            System.out.println("2. Assign Driver");
            System.out.println("3. Complete Trip");
            System.out.println("4. Check Service Due");
            System.out.println("5. Fleet Utilization");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    displayFleet();
                    break;
                case 2:
                    System.out.print("Enter Driver ID: ");
                    String did = sc.next();
                    System.out.print("Enter Vehicle ID: ");
                    String vid = sc.next();
                    assignDriverToVehicle(did, vid);
                    break;
                case 3:
                    System.out.print("Enter Driver ID: ");
                    String drId = sc.next();
                    System.out.print("Fuel used in litres: ");
                    double fuel = sc.nextDouble();
                    for (Driver d : drivers)
                        if (d.driverId.equals(drId)) d.completeTrip(fuel);
                    break;
                case 4:
                    for (Vehicle v : fleet)
                        if (v.checkServiceDue())
                            System.out.println(v.vehicleId + " needs servicing.");
                    break;
                case 5:
                    getFleetUtilization();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
