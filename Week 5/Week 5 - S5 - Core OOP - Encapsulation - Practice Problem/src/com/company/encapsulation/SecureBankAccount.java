package com.company.encapsulation;

public class SecureBankAccount {
    // ================== PRIVATE FIELDS ==================
    private final String accountNumber;   // read-only
    private double balance;               // controlled access
    private int pin;                      // write-only
    private boolean isLocked;             // internal security
    private int failedAttempts;           // failed PIN counter

    // ================== PRIVATE CONSTANTS ==================
    private static final int MAX_FAILED_ATTEMPTS = 3;
    private static final double MIN_BALANCE = 0.0;

    // ================== CONSTRUCTOR ==================
    public SecureBankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = Math.max(initialBalance, MIN_BALANCE);
        this.pin = 0; // must be set later
        this.isLocked = false;
        this.failedAttempts = 0;
        System.out.println("Account " + accountNumber + " created with balance: " + this.balance);
    }

    // ================== ACCOUNT INFO METHODS ==================
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        if (isLocked) {
            System.out.println("Account is locked. Cannot display balance.");
            return -1;
        }
        return balance;
    }

    public boolean isAccountLocked() {
        return isLocked;
    }

    // ================== SECURITY METHODS ==================
    public boolean setPin(int oldPin, int newPin) {
        if (this.pin == oldPin) {
            this.pin = newPin;
            System.out.println("PIN updated successfully.");
            return true;
        }
        System.out.println("Failed to update PIN. Old PIN incorrect.");
        return false;
    }

    public boolean validatePin(int enteredPin) {
        if (isLocked) {
            System.out.println("Account is locked. Access denied.");
            return false;
        }

        if (enteredPin == this.pin) {
            resetFailedAttempts();
            return true;
        } else {
            incrementFailedAttempts();
            return false;
        }
    }

    public boolean unlockAccount(int correctPin) {
        if (this.pin == correctPin) {
            resetFailedAttempts();
            isLocked = false;
            System.out.println("Account unlocked successfully.");
            return true;
        }
        System.out.println("Unlock failed. Wrong PIN.");
        return false;
    }

    // ================== TRANSACTION METHODS ==================
    public void deposit(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: " + amount + ", New Balance: " + balance);
            } else {
                System.out.println("Invalid deposit amount.");
            }
        }
    }

    public void withdraw(double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrew: " + amount + ", Remaining Balance: " + balance);
            } else {
                System.out.println("Withdrawal failed: insufficient funds or invalid amount.");
            }
        }
    }

    public void transfer(SecureBankAccount target, double amount, int pin) {
        if (validatePin(pin)) {
            if (amount > 0 && amount <= balance) {
                this.balance -= amount;
                target.balance += amount;
                System.out.println("Transferred " + amount + " to " + target.getAccountNumber());
            } else {
                System.out.println("Transfer failed: insufficient funds or invalid amount.");
            }
        }
    }

    // ================== PRIVATE HELPERS ==================
    private void lockAccount() {
        isLocked = true;
        System.out.println("Account locked due to too many failed attempts!");
    }

    private void resetFailedAttempts() {
        failedAttempts = 0;
    }

    private void incrementFailedAttempts() {
        failedAttempts++;
        System.out.println("Failed attempts: " + failedAttempts);
        if (failedAttempts >= MAX_FAILED_ATTEMPTS) {
            lockAccount();
        }
    }

    // ================== TEST MAIN ==================
    public static void main(String[] args) {
        SecureBankAccount acc1 = new SecureBankAccount("ACC123", 1000);
        SecureBankAccount acc2 = new SecureBankAccount("ACC456", 500);

        // ❌ Direct access not possible (compile-time errors if you try):
        // acc1.balance = 9999;   <-- Not allowed
        // System.out.println(acc1.pin); <-- Not allowed

        // ✅ Proper usage:
        acc1.setPin(0, 1234); // First time set
        acc2.setPin(0, 4321);

        acc1.deposit(500, 1234);
        acc1.withdraw(200, 1234);

        // ❌ Wrong PIN attempts
        acc1.withdraw(100, 9999);
        acc1.withdraw(100, 9999);
        acc1.withdraw(100, 9999); // This will lock the account

        // ❌ Attempting transactions on locked account
        acc1.deposit(100, 1234);

        // ✅ Unlocking with correct PIN
        acc1.unlockAccount(1234);

        // ✅ Transfer money
        acc1.transfer(acc2, 300, 1234);

        System.out.println("Final Balances:");
        System.out.println("Acc1: " + acc1.getBalance());
        System.out.println("Acc2: " + acc2.getBalance());
    }
}

