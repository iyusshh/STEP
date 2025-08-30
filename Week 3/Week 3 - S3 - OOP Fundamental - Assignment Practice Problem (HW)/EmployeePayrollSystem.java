import java.util.*;

class Employee {
    String empId, empName, department, designation, joinDate, empType;
    double baseSalary;
    boolean[] attendanceRecord = new boolean[30];
    static int totalEmployees = 0;
    static String companyName = "Tech Solutions Pvt Ltd";
    static double totalSalaryExpense = 0;
    static int workingDaysPerMonth = 30;

    Employee(String empId, String empName, String department, String designation, double baseSalary, String joinDate, String empType) {
        this.empId = empId;
        this.empName = empName;
        this.department = department;
        this.designation = designation;
        this.baseSalary = baseSalary;
        this.joinDate = joinDate;
        this.empType = empType;
        totalEmployees++;
    }

    void markAttendance(int day, boolean present) {
        if (day >= 1 && day <= 30) attendanceRecord[day - 1] = present;
    }

    int getTotalPresentDays() {
        int count = 0;
        for (boolean present : attendanceRecord) if (present) count++;
        return count;
    }

    int getAllowedLeaves() {
        switch (empType.toLowerCase()) {
            case "full-time": return 4;
            case "part-time": return 2;
            case "contract": return 1;
            default: return 2;
        }
    }

    double calculateSalary() {
        int presentDays = getTotalPresentDays();
        double perDaySalary = baseSalary / workingDaysPerMonth;
        double salary = perDaySalary * presentDays;
        double bonus = calculateBonus();
        double total = salary + bonus;
        totalSalaryExpense += total;
        return total;
    }

    double calculateBonus() {
        if (getTotalPresentDays() >= 28) return baseSalary * 0.2;
        else if (getTotalPresentDays() >= 25) return baseSalary * 0.1;
        else return 0;
    }

    void generatePaySlip() {
        System.out.println("\n--- Pay Slip ---");
        System.out.println("Company: " + companyName);
        System.out.println("Employee: " + empName + " (" + empId + ")");
        System.out.println("Designation: " + designation);
        System.out.println("Base Salary: Rs." + baseSalary);
        System.out.println("Present Days: " + getTotalPresentDays());
        System.out.println("Bonus: Rs." + calculateBonus());
        System.out.println("Net Salary: Rs." + calculateSalary());
    }

    void requestLeave(int leaveDays) {
        if (leaveDays <= getAllowedLeaves()) System.out.println(empName + " leave approved for " + leaveDays + " days.");
        else System.out.println(empName + " leave request denied! Allowed only " + getAllowedLeaves() + " days.");
    }
}

class Department {
    String deptId, deptName;
    Employee manager;
    Employee[] employees;
    double budget;

    Department(String deptId, String deptName, Employee manager, Employee[] employees, double budget) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.manager = manager;
        this.employees = employees;
        this.budget = budget;
    }

    double getDepartmentExpenses() {
        double total = 0;
        for (Employee e : employees) total += e.calculateSalary();
        return total;
    }
}

public class EmployeePayrollSystem {
    static double calculateCompanyPayroll(Employee[] employees) {
        double total = 0;
        for (Employee e : employees) total += e.calculateSalary();
        return total;
    }

    static void getDepartmentWiseExpenses(Department[] departments) {
        for (Department d : departments)
            System.out.println("Department: " + d.deptName + " | Expenses: Rs." + d.getDepartmentExpenses());
    }

    static void getAttendanceReport(Employee[] employees) {
        for (Employee e : employees)
            System.out.println(e.empName + ": Present " + e.getTotalPresentDays() + " days");
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("E001", "Aayush Rai", "IT", "Developer", 60000, "01-04-2024", "full-time");
        Employee e2 = new Employee("E002", "Priya Sharma", "HR", "HR Manager", 50000, "15-03-2023", "full-time");
        Employee e3 = new Employee("E003", "Rohan Gupta", "IT", "Tester", 30000, "12-02-2024", "part-time");

        Employee[] employees = {e1, e2, e3};
        Department d1 = new Department("D001", "IT", e1, new Employee[]{e1, e3}, 300000);
        Department d2 = new Department("D002", "HR", e2, new Employee[]{e2}, 150000);
        Department[] departments = {d1, d2};

        e1.markAttendance(1, true);
        e1.markAttendance(2, true);
        e1.markAttendance(3, false);
        e2.markAttendance(1, true);
        e2.markAttendance(2, true);
        e3.markAttendance(1, false);
        e3.markAttendance(2, true);

        e1.generatePaySlip();
        e2.generatePaySlip();
        e3.generatePaySlip();

        System.out.println("\n--- Company Payroll ---");
        System.out.println("Total Payroll: Rs." + calculateCompanyPayroll(employees));

        System.out.println("\n--- Department Expenses ---");
        getDepartmentWiseExpenses(departments);

        System.out.println("\n--- Attendance Report ---");
        getAttendanceReport(employees);

        e1.requestLeave(3);
        e3.requestLeave(3);
    }
}
