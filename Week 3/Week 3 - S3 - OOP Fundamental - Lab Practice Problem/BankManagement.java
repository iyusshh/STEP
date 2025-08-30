class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private static int totalAccounts = 0;

    public BankAccount(String accountHolderName, double initialDeposit) {
        if (initialDeposit < 0) {
            System.out.println("Initial deposit cannot be negative. Setting to 0.");
            initialDeposit = 0;
        }
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.accountNumber = generateAccountNumber();
    }

    private static String generateAccountNumber() {
        totalAccounts++;
        return String.format("ACC%03d", totalAccounts);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return;
        }
        balance += amount;
        System.out.println("Rs." + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed!");
            return;
        }
        balance -= amount;
        System.out.println("Rs." + amount + " withdrawn successfully.");
    }

    public void checkBalance() {
        System.out.println("Current Balance: Rs." + balance);
    }

    public void displayAccountInfo() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number  : " + accountNumber);
        System.out.println("Account Holder  : " + accountHolderName);
        System.out.println("Balance         : Rs." + balance);
        System.out.println("------------------------");
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }
}

public class BankManagement {
    public static void main(String[] args) {
        BankAccount[] accounts = new BankAccount[3];

        accounts[0] = new BankAccount("Aayush Rai", 5000);
        accounts[1] = new BankAccount("Rohan Sharma", 10000);
        accounts[2] = new BankAccount("Priya Singh", 2000);

        accounts[0].deposit(2000);
        accounts[0].withdraw(1000);
        accounts[0].checkBalance();

        accounts[1].withdraw(12000);
        accounts[2].deposit(3000);

        for (int i = 0; i < accounts.length; i++) {
            accounts[i].displayAccountInfo();
        }

        System.out.println("\nTotal Bank Accounts Created: " + BankAccount.getTotalAccounts());
    }
}
