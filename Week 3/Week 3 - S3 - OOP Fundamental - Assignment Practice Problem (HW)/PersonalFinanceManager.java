import java.util.Scanner;

class PersonalAccount {
    private String accountHolderName;
    private String accountNumber;
    private double currentBalance;
    private double totalIncome;
    private double totalExpenses;

    private static int totalAccounts = 0;
    private static String bankName = "Default Bank";

    public PersonalAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.currentBalance = 0.0;
        this.totalIncome = 0.0;
        this.totalExpenses = 0.0;
        totalAccounts++;
    }

    public static void setBankName(String name) {
        bankName = name;
    }

    public static String getBankName() {
        return bankName;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }

    private static String generateAccountNumber() {
        return "ACC" + (1000 + totalAccounts + 1);
    }

    public void addIncome(double amount, String description) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Income amount must be greater than zero.");
            }
            currentBalance += amount;
            totalIncome += amount;
            System.out.println("Income Added: Rs." + amount + " | Description: " + description);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void addExpense(double amount, String description) {
        try {
            if (amount <= 0) {
                throw new IllegalArgumentException("Expense amount must be greater than zero.");
            }
            if (amount > currentBalance) {
                throw new IllegalArgumentException("Insufficient balance for this expense!");
            }
            currentBalance -= amount;
            totalExpenses += amount;
            System.out.println("Expense Added: Rs." + amount + " | Description: " + description);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public double calculateSavings() {
        return totalIncome - totalExpenses;
    }

    public void displayAccountSummary() {
        System.out.println("\n====== Account Summary ======");
        System.out.println("Bank Name        : " + bankName);
        System.out.println("Account Holder   : " + accountHolderName);
        System.out.println("Account Number   : " + accountNumber);
        System.out.println("Total Income     : Rs." + totalIncome);
        System.out.println("Total Expenses   : Rs." + totalExpenses);
        System.out.println("Current Balance  : Rs." + currentBalance);
        System.out.println("Savings          : Rs." + calculateSavings());
        System.out.println("==============================\n");
    }
}

public class PersonalFinanceManager {
    public static void main(String[] args) {
        PersonalAccount.setBankName("Aayush National Bank");

        PersonalAccount acc1 = new PersonalAccount("Aayush Rai");
        PersonalAccount acc2 = new PersonalAccount("Rahul Verma");
        PersonalAccount acc3 = new PersonalAccount("Priya Sharma");

        acc1.addIncome(25000, "Salary Credit");
        acc1.addExpense(5000, "Groceries");
        acc1.addExpense(2000, "Movie Tickets");

        acc2.addIncome(30000, "Freelance Project");
        acc2.addExpense(8000, "Rent Payment");
        acc2.addExpense(3000, "Shopping");

        acc3.addIncome(15000, "Part-time Job");
        acc3.addExpense(2000, "Electricity Bill");
        acc3.addExpense(1000, "Snacks");

        acc1.displayAccountSummary();
        acc2.displayAccountSummary();
        acc3.displayAccountSummary();

        System.out.println("Total Accounts Created: " + PersonalAccount.getTotalAccounts());
        System.out.println("\nBank Name is shared among all accounts: " + PersonalAccount.getBankName());
    }
}
