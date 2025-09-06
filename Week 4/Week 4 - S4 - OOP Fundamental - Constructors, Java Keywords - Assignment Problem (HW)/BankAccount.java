import java.util.Random;

public class BankAccount {
    String accountHolder;
    int accountNumber;
    double balance;

    // Default constructor
    public BankAccount() {
        this("Unknown", 0.0);
    }

    // Constructor with name
    public BankAccount(String accountHolder) {
        this(accountHolder, 0.0);
    }

    // Constructor with name and initial balance
    public BankAccount(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = new Random().nextInt(900000) + 100000; // 6-digit account number
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(accountHolder + " deposited: Rs." + amount);
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(accountHolder + " withdrew: Rs." + amount);
        } else {
            System.out.println("Insufficient balance for " + accountHolder);
        }
    }

    public void displayAccount() {
        System.out.println("\n=== ACCOUNT DETAILS ===");
        System.out.println("Holder: " + accountHolder);
        System.out.println("Account No: " + accountNumber);
        System.out.println("Balance: Rs." + balance);
    }

    public static void main(String[] args) {
        BankAccount a1 = new BankAccount();
        BankAccount a2 = new BankAccount("Alice");
        BankAccount a3 = new BankAccount("Bob", 5000.0);

        a1.deposit(1000);
        a2.deposit(2000);
        a3.withdraw(1500);

        a1.displayAccount();
        a2.displayAccount();
        a3.displayAccount();
    }
}


