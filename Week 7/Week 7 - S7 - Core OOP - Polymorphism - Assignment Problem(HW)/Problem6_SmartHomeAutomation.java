abstract class SmartDevice {}
class SmartTV extends SmartDevice {
    void stream(){ System.out.println("Streaming on Smart TV"); }
}
class SmartThermostat extends SmartDevice {
    void setTemp(){ System.out.println("Temperature set by Thermostat"); }
}
class SmartSecurity extends SmartDevice {
    void arm(){ System.out.println("Security System Armed"); }
}

public class Problem6_SmartHomeAutomation {
    public static void main(String[] args) {
        SmartDevice[] devices = { new SmartTV(), new SmartThermostat(), new SmartSecurity() };
        for (SmartDevice d : devices) {
            if (d instanceof SmartTV) ((SmartTV)d).stream();
            else if (d instanceof SmartThermostat) ((SmartThermostat)d).setTemp();
            else if (d instanceof SmartSecurity) ((SmartSecurity)d).arm();
        }
    }
}
