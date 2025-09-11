package com.company.javabeans;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Objects;

/**
 * EmployeeBean class demonstrating JavaBean standards.
 * - Implements Serializable
 * - Has private fields
 * - Provides no-arg + parameterized constructors
 * - Provides standard getters and setters
 * - Provides computed and derived properties
 */
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // ============ PRIVATE FIELDS ============
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private LocalDate hireDate;
    private boolean isActive;

    // ============ CONSTRUCTORS ============
    // No-arg constructor (JavaBean requirement)
    public EmployeeBean() {
    }

    // Parameterized constructor
    public EmployeeBean(String employeeId, String firstName, String lastName,
                        double salary, String department, LocalDate hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        setSalary(salary); // use setter for validation
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    // ============ STANDARD GETTERS ============
    public String getEmployeeId() { return employeeId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public double getSalary() { return salary; }
    public String getDepartment() { return department; }
    public LocalDate getHireDate() { return hireDate; }
    public boolean isActive() { return isActive; }

    // ============ STANDARD SETTERS ============
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setDepartment(String department) { this.department = department; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    public void setActive(boolean active) { isActive = active; }

    // salary setter with validation
    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary must be positive.");
        }
        this.salary = salary;
    }

    // ============ COMPUTED PROPERTIES ============
    public String getFullName() {
        return (firstName != null ? firstName : "") + " " +
               (lastName != null ? lastName : "");
    }

    public int getYearsOfService() {
        if (hireDate == null) return 0;
        return Period.between(hireDate, LocalDate.now()).getYears();
    }

    public String getFormattedSalary() {
        return NumberFormat.getCurrencyInstance().format(salary);
    }

    // ============ DERIVED PROPERTIES ============
    public void setFullName(String fullName) {
        if (fullName == null || !fullName.contains(" ")) {
            throw new IllegalArgumentException("Full name must include first and last name.");
        }
        String[] parts = fullName.split(" ", 2);
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    // ============ OVERRIDES ============
    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", salary=" + getFormattedSalary() +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                ", yearsOfService=" + getYearsOfService() +
                ", isActive=" + isActive +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeBean)) return false;
        EmployeeBean that = (EmployeeBean) o;
        return Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    // ============ DEMO MAIN ============
    public static void main(String[] args) {
        // Using default constructor + setters
        EmployeeBean emp1 = new EmployeeBean();
        emp1.setEmployeeId("E001");
        emp1.setFullName("Alice Johnson");
        emp1.setSalary(60000);
        emp1.setDepartment("HR");
        emp1.setHireDate(LocalDate.of(2018, 5, 10));
        emp1.setActive(true);

        // Using parameterized constructor
        EmployeeBean emp2 = new EmployeeBean("E002", "Bob", "Smith",
                75000, "Finance", LocalDate.of(2020, 3, 15), true);

        // Demonstrating getters
        System.out.println("Employee 1 Name: " + emp1.getFullName());
        System.out.println("Employee 2 Salary: " + emp2.getFormattedSalary());

        // Testing computed properties
        System.out.println("Years of Service (emp1): " + emp1.getYearsOfService());

        // Testing validation
        try {
            emp1.setSalary(-5000);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation caught: " + e.getMessage());
        }

        // Show employees
        System.out.println(emp1);
        System.out.println(emp2);

        // Collections demo
        java.util.List<EmployeeBean> employees = java.util.Arrays.asList(emp1, emp2);
        employees.stream()
                .filter(EmployeeBean::isActive)
                .sorted((a, b) -> Double.compare(b.getSalary(), a.getSalary()))
                .forEach(System.out::println);
    }
}
