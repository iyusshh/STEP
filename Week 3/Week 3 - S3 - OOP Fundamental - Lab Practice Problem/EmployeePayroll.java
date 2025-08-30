class Employee {
    private String empId, empName, department, empType;
    private double baseSalary;
    private static int totalEmployees = 0;

    public Employee(String empId, String empName, String department, double baseSalary) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-Time";
        totalEmployees++;
    }

    public Employee(String empId, String empName, String department, double hourlyRate, int hoursWorked) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = hourlyRate * hoursWorked;
        this.empType = "Part-Time";
        totalEmployees++;
    }

    public Employee(String empId, String empName, String department, double contractAmount, boolean isContract) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.baseSalary = contractAmount;
        this.empType = "Contract";
        totalEmployees++;
    }

    public double calculateSalary(double bonus) {
        return baseSalary + bonus; // Full-time
    }

    public double calculateSalary(int hoursWorked, double hourlyRate) {
        return hourlyRate * hoursWorked; // Part-time
    }

    public double calculateSalary() {
        return baseSalary; // Contract
    }

    public double calculateTax(double salary, double taxRate) {
        return salary * taxRate / 100;
    }

    public void generatePaySlip() {
        double salary;
        double tax;

        if (empType.equals("Full-Time")) {
            salary = calculateSalary(5000); 
            tax = calculateTax(salary, 10); 
        } else if (empType.equals("Part-Time")) {
            salary = baseSalary; 
            tax = calculateTax(salary, 5); 
        } else {
            salary = calculateSalary();
            tax = calculateTax(salary, 8); 
        }

        System.out.println("\n--- Pay Slip ---");
        System.out.println("ID: " + empId + " | Name: " + empName + " | Dept: " + department);
        System.out.println("Type: " + empType);
        System.out.println("Salary: Rs." + salary);
        System.out.println("Tax: Rs." + tax);
        System.out.println("Net Pay: Rs." + (salary - tax));
    }

    public void displayEmployeeInfo() {
        System.out.println(empId + " | " + empName + " | " + department + " | " + empType + " | Rs." + baseSalary);
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }
}

public class EmployeePayroll {
    public static void main(String[] args) {
        Employee e1 = new Employee("E001", "Aayush Rai", "IT", 40000);                
        Employee e2 = new Employee("E002", "Rohan Sharma", "HR", 500, 40);           
        Employee e3 = new Employee("E003", "Priya Singh", "Finance", 60000, true);  

        System.out.println("--- Employee Details ---");
        e1.displayEmployeeInfo();
        e2.displayEmployeeInfo();
        e3.displayEmployeeInfo();

        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        System.out.println("\nTotal Employees: " + Employee.getTotalEmployees());
    }
}
