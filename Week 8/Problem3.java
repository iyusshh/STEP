// File: Problem3.java
interface PaymentGateway {
    void pay(double amount);
    void refund(double amount);
}

class CreditCardPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to Credit Card");
    }
}

class UPIPayment implements PaymentGateway {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via UPI");
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunded " + amount + " to UPI");
    }
}

public class Problem3 {
    public static void main(String[] args) {
        PaymentGateway p1 = new CreditCardPayment();
        p1.pay(500);
        p1.refund(200);

        PaymentGateway p2 = new UPIPayment();
        p2.pay(1000);
        p2.refund(500);
    }
}
