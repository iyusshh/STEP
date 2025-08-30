class Vehicle {
    private String vehicleId, brand, model;
    private double rentPerDay;
    private boolean isAvailable;

    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static int rentalDays = 0;
    private static String companyName = "Not Set";

    public Vehicle(String vehicleId, String brand, String model, double rentPerDay) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        totalVehicles++;
    }

    public double calculateRent(int days) {
        double amount = rentPerDay * days;
        totalRevenue += amount;
        rentalDays += days;
        return amount;
    }

    public void rentVehicle(int days) {
        if (isAvailable) {
            double amount = calculateRent(days);
            isAvailable = false;
            System.out.println(brand + " " + model + " rented for " + days + " days. Rent: Rs." + amount);
        } else {
            System.out.println(brand + " " + model + " is already rented.");
        }
    }

    public void returnVehicle() {
        if (!isAvailable) {
            isAvailable = true;
            System.out.println(brand + " " + model + " returned and is now available.");
        } else {
            System.out.println(brand + " " + model + " was not rented.");
        }
    }

    public void displayVehicleInfo() {
        System.out.println(vehicleId + " | " + brand + " " + model + " | Rs." + rentPerDay + "/day | " +
                (isAvailable ? "Available" : "Rented"));
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        return rentalDays == 0 ? 0 : totalRevenue / rentalDays;
    }

    public static void displayCompanyStats() {
        System.out.println("\n--- " + companyName + " Rental Stats ---");
        System.out.println("Total Vehicles: " + totalVehicles);
        System.out.println("Total Revenue: Rs." + totalRevenue);
        System.out.println("Total Rental Days: " + rentalDays);
        System.out.println("Average Rent/Day: Rs." + getAverageRentPerDay());
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle.setCompanyName("ZoomRide Rentals");

        Vehicle v1 = new Vehicle("V001", "Toyota", "Innova", 1500);
        Vehicle v2 = new Vehicle("V002", "Honda", "City", 1200);
        Vehicle v3 = new Vehicle("V003", "Hyundai", "Creta", 1000);

        v1.rentVehicle(3);
        v2.rentVehicle(2);
        v1.returnVehicle();
        v3.rentVehicle(4);

        System.out.println("\n--- Vehicle Info ---");
        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        Vehicle.displayCompanyStats();
    }
}
