public class GameController {
    private String controllerBrand;
    private String connectionType;
    private boolean hasVibration;
    private int batteryLevel;
    private double sensitivity;

    // Default constructor
    public GameController() {
        this.controllerBrand = "GenericPad";
        this.connectionType = "USB";
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    // Parameterized constructor
    public GameController(String controllerBrand, String connectionType,
                          boolean hasVibration, int batteryLevel, double sensitivity) {
        this.controllerBrand = controllerBrand;
        this.connectionType = connectionType;
        this.hasVibration = hasVibration;
        if (batteryLevel < 0) batteryLevel = 0;
        if (batteryLevel > 100) batteryLevel = 100;
        this.batteryLevel = batteryLevel;
        if (sensitivity < 0.1) sensitivity = 0.1;
        if (sensitivity > 3.0) sensitivity = 3.0;
        this.sensitivity = sensitivity;
    }

    // Two-parameter convenience constructor
    public GameController(String brand, String connectionType) {
        this.controllerBrand = brand;
        this.connectionType = connectionType;
        this.hasVibration = true;
        this.batteryLevel = 100;
        this.sensitivity = 1.0;
    }

    public void calibrateController() {
        System.out.println("Calibrating " + controllerBrand + " controller...");
    }

    public void displayConfiguration() {
        System.out.println("Brand: " + controllerBrand);
        System.out.println("Connection: " + connectionType);
        System.out.println("Vibration: " + (hasVibration ? "Enabled" : "Disabled"));
        System.out.println("Battery Level: " + batteryLevel + "%");
        System.out.println("Sensitivity: " + sensitivity);
    }

    public void testVibration() {
        if (hasVibration) {
            System.out.println("*BUZZ* Vibration test successful!");
        } else {
            System.out.println("Vibration disabled on this controller.");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== GAMING CONTROLLER SETUP ===");

        GameController controller1 = new GameController();
        GameController controller2 = new GameController("ProPad", "Bluetooth", false, 120, 4.5);
        GameController controller3 = new GameController("ElitePad", "Wireless");

        System.out.println("\n--- Controller 1 ---");
        controller1.displayConfiguration();
        controller1.calibrateController();
        controller1.testVibration();

        System.out.println("\n--- Controller 2 ---");
        controller2.displayConfiguration();
        controller2.calibrateController();
        controller2.testVibration();

        System.out.println("\n--- Controller 3 ---");
        controller3.displayConfiguration();
        controller3.calibrateController();
        controller3.testVibration();
    }
}

