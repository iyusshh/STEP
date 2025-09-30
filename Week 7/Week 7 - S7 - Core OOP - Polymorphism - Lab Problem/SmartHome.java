// File: SmartHome.java

class SmartDevice {
    protected String name;

    public SmartDevice(String name) {
        this.name = name;
    }

    public void turnOn() {
        System.out.println(name + " is turning on.");
    }

    public void turnOff() {
        System.out.println(name + " is turning off.");
    }
}

class SmartLight extends SmartDevice {
    public SmartLight(String name) {
        super(name);
    }

    public void changeColor(String color) {
        System.out.println(name + " is changing color to " + color + ".");
    }
}

class SmartThermostat extends SmartDevice {
    public SmartThermostat(String name) {
        super(name);
    }

    public void setTemperature(int degrees) {
        System.out.println(name + " is setting temperature to " + degrees + " degrees.");
    }
}

public class SmartHome {
    public static void main(String[] args) {
        SmartDevice[] devices = {
            new SmartLight("Living Room Light"),
            new SmartThermostat("Main Thermostat"),
            new SmartLight("Bedroom Lamp")
        };

        System.out.println("--- Controlling Smart Home Devices ---");
        for (SmartDevice device : devices) {
            device.turnOn();

            if (device instanceof SmartLight) {
                SmartLight light = (SmartLight) device;
                light.changeColor("Blue");
            } else if (device instanceof SmartThermostat) {
                SmartThermostat thermostat = (SmartThermostat) device;
                thermostat.setTemperature(22);
            }
            
            device.turnOff();
            System.out.println("----------");
        }
    }
}